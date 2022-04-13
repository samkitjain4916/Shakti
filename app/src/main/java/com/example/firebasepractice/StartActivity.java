package com.example.firebasepractice;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity
{
    Button signUp,signIn;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(getSupportActionBar()!=null)
        {getSupportActionBar().hide();}

        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.signIn);
        image  = findViewById(R.id.imageView);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StartActivity.this,signInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}