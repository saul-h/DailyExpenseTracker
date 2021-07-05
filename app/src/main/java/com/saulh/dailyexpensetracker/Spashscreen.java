package com.saulh.dailyexpensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Spashscreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    private static final String TAG = "MyActivity";
    public static String this_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spashscreen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false);
                String username = sharedPreferences.getString("username", " ");

                if(hasLoggedIn){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    this_username = sharedPreferences.getString("username", "no username");
                    Log.d(TAG,"username from splashscreen " + this_username);
                    finish(); //kill this activity
                }else{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish(); //kill this activity
                }


            }
        }, SPLASH_TIME_OUT);
    }
}