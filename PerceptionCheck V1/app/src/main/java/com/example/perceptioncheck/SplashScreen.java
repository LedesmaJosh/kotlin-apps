package com.example.perceptioncheck;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private final int MainActivityDelay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent switchtoSheet = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(switchtoSheet);
                finish();

            }
        }, MainActivityDelay);
    }
}