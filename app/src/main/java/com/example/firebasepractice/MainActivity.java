package com.example.firebasepractice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.firebasepractice.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    public ActivityMainBinding binding;
    public NavigationView navigationView;
    public static String user_name="User",user_email="Email",user_age="18";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.appBarMain.fab.setVisibility(View.GONE);
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            logout();
            return true;
        });

        navigationView.getMenu().findItem(R.id.nav_admin).setOnMenuItemClickListener(menuItem -> {
           open_administrator();
            return true;
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder
        (R.id.nav_home, R.id.nav_gallery,R.id.nav_shakti,R.id.nav_mail,R.id.nav_developer,R.id.nav_contact,R.id.nav_sapne).setOpenableLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        getData("userName","userEmail","userAge"); //static_data is set now
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this, "Signed-Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,StartActivity.class));
        finish();
    }

    public void open_administrator() {
        startActivity(new Intent(MainActivity.this, LockActivity.class));
    }

    @SuppressLint("SetTextI18n")
    public void setNav_header_info() {
        View headerView = navigationView.getHeaderView(0);
        TextView nav_header_name  = headerView.findViewById(R.id.nav_header_name);
        TextView nav_header_about = headerView.findViewById(R.id.nav_header_about);
        TextView nav_header_age   = headerView.findViewById(R.id.nav_header_age);
        nav_header_name.setText(user_name);
        nav_header_about.setText(user_email);
        nav_header_age.setText(user_age + " years");
    }

    public void getData(String key1,String key2,String key3) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        String userId = auth.getUid();

        DocumentReference documentReference = null;
        if (userId != null) {
            documentReference = fStore.collection("users").document(userId);
        }
        if (documentReference != null) {
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                    if(value!=null)
                    {
                         user_name  = value.getString(key1);
                         user_email = value.getString(key2);
                         user_age   = value.getString(key3);
                         setNav_header_info();
                    }
                }
            });
        }

    }

}