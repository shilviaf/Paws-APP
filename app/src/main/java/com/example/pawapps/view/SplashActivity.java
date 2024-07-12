package com.example.pawapps.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.pawapps.R;

public class SplashActivity extends AppCompatActivity {
    private int waktu_loading=4000;

    //2000=2 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent StockActivity=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(StockActivity);
                finish();

            }
        },waktu_loading);
    }
}