package com.example.finbuddy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.finbuddy.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(2200, 2200) {
            public void onTick(long millisUntilFinished) { }

            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }.start();
    }
}