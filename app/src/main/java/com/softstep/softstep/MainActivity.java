package com.softstep.softstep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
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

        Typeface typeface = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            typeface = getResources().getFont(R.font.typo_round_regular_demo);
        };


        int i = 0;
        // Dynamically create and add buttons for each exercise
        for (Exercise exercise : exercises) {
            i++;
            Button exerciseButton = new Button(this);
            exerciseButton.setText(exercise.getName());
            exerciseButton.setTypeface(typeface);
            int finalI = i;
            exerciseButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                intent.putExtra("EXERCISE_ID", String.valueOf(finalI));
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clicked on " + exercise.getName(), Toast.LENGTH_SHORT).show();

            });

            // Set layout parameters for the button
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(16, 16, 16, 16);
            exerciseButton.setLayoutParams(layoutParams);
            exerciseButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_button, null));
            exerciseButton.setTextColor(getResources().getColor(android.R.color.white));

            // Add the button to the LinearLayout container
            exercisesContainer.addView(exerciseButton);
        }
    }
}

