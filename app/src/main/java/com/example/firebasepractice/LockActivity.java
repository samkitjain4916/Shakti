package com.example.firebasepractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LockActivity extends AppCompatActivity {

    private String real_password,pass1="",pass2="";
    private EditText text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        text_password = findViewById(R.id.text_password);
        Button go = findViewById(R.id.go);

        fetchPassword("Password1","Password2");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                real_password = text_password.getText().toString();
                if(pass1.equals(real_password) || pass2.equals(real_password))
                {
                    startActivity(new Intent(LockActivity.this,UserDataActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(LockActivity.this,"Wrong Key!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void fetchPassword(String key1,String key2) {

        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference;
        documentReference = fStore.collection("lock").document("xou8fCbR2np1PxNAdD7K");

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value != null) {
                    pass1 = value.getString(key1);
                    pass2 = value.getString(key2);
                }
            }
        });
    }

}