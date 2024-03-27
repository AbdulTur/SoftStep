package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ImageButton for the profile
        ImageButton profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            Toast.makeText(getApplicationContext(), "Profile Button was clicked", Toast.LENGTH_SHORT).show();
        });

        // Get the LinearLayout container
        LinearLayout exercisesContainer = findViewById(R.id.exercisesContainer);

        // Load the exercises from the JSON file in internal storage
        ExerciseJsonHandler exerciseJsonHandler = new ExerciseJsonHandler();
        List<Exercise> exercises = exerciseJsonHandler.loadExercises(this);

        // Dynamically create and add buttons for each exercise
        for (Exercise exercise : exercises) {
            Button exerciseButton = new Button(this);
            exerciseButton.setText(exercise.getName());
            exerciseButton.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "Clicked on " + exercise.getName(), Toast.LENGTH_SHORT).show();

            });

            // Set layout parameters for the button
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(16, 16, 16, 16); // Adjust margins as needed
            exerciseButton.setLayoutParams(layoutParams);

            // Style the button (optional)
            exerciseButton.setBackgroundColor(getResources().getColor(R.color.soft_blue));
            exerciseButton.setTextColor(getResources().getColor(android.R.color.white));

            // Add the button to the LinearLayout container
            exercisesContainer.addView(exerciseButton);
        }
    }
}
