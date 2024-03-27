package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        SharedPreferences sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isFirstLaunch = sharedPref.getBoolean("isFirstLaunch", true);

        if (isFirstLaunch) {
            // The app is being launched for the first time
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isFirstLaunch", false);
            editor.apply();

            // Start the activity you want to launch initially
            startActivity(new Intent(this, ProfileActivity.class));
        } else {
            // Start the other activity directly
            startActivity(new Intent(this, MainActivity.class));
        }

        // Finish the launcher activity
        finish();
    }
}