package com.example.firebasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar()!=null)
        {getSupportActionBar().hide();}

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread(){
            public void run()
            {
                try {
                    sleep(2000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashActivity.this,StartActivity.class));
                    finish();
                }
            }
         };
        thread.start();

    }
}