package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            Toast.makeText(getApplicationContext(), "Profile Button was clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton legLiftButton = findViewById(R.id.legLift);

        legLiftButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Exercise1Activity.class));
            Toast.makeText(getApplicationContext(), "Leg Lift Button was clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton armCirclesButton = findViewById(R.id.armCircles);

        armCirclesButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Exercise2Activity.class));
            Toast.makeText(getApplicationContext(), "Heel to Toe Button was clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton heelToToeButton = findViewById(R.id.heelToToe);

        heelToToeButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Exercise3Activity.class));
            Toast.makeText(getApplicationContext(), "Heel to Toe Button was clicked", Toast.LENGTH_SHORT).show();
        });


    }
}