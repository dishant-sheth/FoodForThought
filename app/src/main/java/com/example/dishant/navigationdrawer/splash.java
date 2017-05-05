package com.example.dishant.navigationdrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by dishant on 29/3/17.
 */

public class splash extends AppCompatActivity {

    private boolean backPress;
    private static final int DURATION = 4000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

                if(!backPress){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        }, DURATION);

    }

    @Override
    public void onBackPressed() {
        backPress = true;
        super.onBackPressed();
    }
}
