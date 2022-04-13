package com.example.firebasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signInActivity extends AppCompatActivity
{
    private EditText signin_email,signin_password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        auth            = FirebaseAuth.getInstance();
        signin_email    = findViewById(R.id.signin_email);
        signin_password = findViewById(R.id.signin_password);
        Button signin_button = findViewById(R.id.signin_button);
        TextView forgot_pass = findViewById(R.id.forgot_pass);

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passReset = new AlertDialog.Builder(view.getContext());
                passReset.setTitle("Reset Password ?");
                passReset.setMessage("Enter your registered email to receive password reset link");
                passReset.setView(resetMail);

                passReset.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passReset.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        String mail = resetMail.getText().toString();

                        if(mail.isEmpty())
                        {
                            Toast.makeText(signInActivity.this,"Please enter email!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(signInActivity.this,"Reset link sent to your email!!",Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signInActivity.this,"This email is not registered!!",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

                passReset.create().show();

            }
        });

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email    = signin_email.getText().toString();
                String password = signin_password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(signInActivity.this,"Invalid or empty credentials!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUser(email, password);
                }
            }
        });


    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(signInActivity.this, "Sign-In Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signInActivity.this,MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(signInActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}