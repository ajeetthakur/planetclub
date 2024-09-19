package com.app.planetclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SplashScreen extends Activity {

    private static int splashTimeOut = 3000;
    ImageView splash_logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_logo = findViewById(R.id.splash_logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, PlanetClubWebView.class);
                startActivity(i);

                finish();
            }
        }, splashTimeOut);



    }
}
