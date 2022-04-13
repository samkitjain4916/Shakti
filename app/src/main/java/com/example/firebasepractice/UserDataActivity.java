package com.example.firebasepractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserDataActivity extends AppCompatActivity {

    private ArrayList<User>userArrayList;
    private MyAdapter myAdapter;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdapter = new MyAdapter(UserDataActivity.this,userArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListner();

    }

    private void EventChangeListner() {

        db.collection("shaktiRegistrations").orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {
                if(error!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                    return;
                }

                assert value != null;
                for (DocumentChange dc : value.getDocumentChanges())
                {
                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {
                        userArrayList.add(dc.getDocument().toObject(User.class));
                    }


                    myAdapter.notifyDataSetChanged();


                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

            }
        });

    }
}