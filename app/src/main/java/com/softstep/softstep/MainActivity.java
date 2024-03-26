package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BEGIN DATABASE TEST
        //TEST PRINTS EXERCISE ID TO LOGCAT

        dbManager = new DatabaseManager(this);

        try {
            dbManager.open();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Cursor cursor = dbManager.fetch();

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXERCISE_ID));
                System.out.println(ID);
            } while (cursor.moveToNext());
        }

        //END DATABASE TEST

        ImageButton profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            Toast.makeText(getApplicationContext(), "Profile Button was clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton legLiftButton = findViewById(R.id.legLift);

        legLiftButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
            intent.putExtra("EXERCISE_ID", "1");
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Leg Lift Button was clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton armCirclesButton = findViewById(R.id.armCircles);

        armCirclesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
            intent.putExtra("EXERCISE_ID", "2");
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Heel to Toe Button was clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton heelToToeButton = findViewById(R.id.heelToToe);

        heelToToeButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
            intent.putExtra("EXERCISE_ID", "3");
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Heel to Toe Button was clicked", Toast.LENGTH_SHORT).show();
        });


    }
}