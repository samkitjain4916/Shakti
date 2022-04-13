package com.example.firebasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText name,email,password,mobileNumber,aadharNumber,address,age;
    private RadioButton radioM,radioF;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.submit);

        name         = findViewById(R.id.name);
        email        = findViewById(R.id.email);
        password     = findViewById(R.id.password);
        age          = findViewById(R.id.ageNumber);
        mobileNumber = findViewById(R.id.mobileNumber);
        aadharNumber = findViewById(R.id.aadharNumber);
        address      = findViewById(R.id.address);
        radioM       = findViewById(R.id.radioM);
        radioF       = findViewById(R.id.radioF);

        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String text_email        = email.getText().toString();
                String text_pass         = password.getText().toString();
                String text_name         = name.getText().toString();
                String text_age          = age.getText().toString();
                String text_mobileNumber = mobileNumber.getText().toString();
                String text_aadharNumber = aadharNumber.getText().toString();
                String text_address      = address.getText().toString();
                String text_gender       = "Unknown";

                if(radioM.isChecked()){
                    text_gender = "Male";
                }
                if(radioF.isChecked()){
                    text_gender = "Female";
                }

                if(TextUtils.isEmpty(text_name) || TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_pass) || TextUtils.isEmpty(text_age))
                {
                    Toast.makeText(RegisterActivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
                else if(text_pass.length()<6)
                {
                    Toast.makeText(RegisterActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                else if(text_age.length()==0 || text_age.length()>2)
                {
                    Toast.makeText(RegisterActivity.this,"Invalid Age",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registerUser(text_email,text_pass,text_name,text_age,text_mobileNumber,text_aadharNumber,text_address,text_gender);
                }
            }
        });

    }

    private void registerUser(String text_email, String text_pass, String text_name,String text_age,String text_mobileNumber,String text_aadharNumber,String text_address,String text_gender) {
        auth.createUserWithEmailAndPassword(text_email,text_pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userId);

                    Map<String,Object>map = new HashMap<>();
                    map.put("userName",text_name);
                    map.put("userEmail",text_email);
                    map.put("userAge",text_age);
                    map.put("userMobileNumber",text_mobileNumber);
                    map.put("userAadharNumber",text_aadharNumber);
                    map.put("userAddress",text_address);
                    map.put("userGender",text_gender);

                    documentReference.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this,"Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"!Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                                finish();
                            }

                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}