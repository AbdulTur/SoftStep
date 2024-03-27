package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        ImageButton profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            Toast.makeText(getApplicationContext(), "Profile Button was clicked", Toast.LENGTH_SHORT).show();
        });

        LinearLayout exercisesContainer = findViewById(R.id.exercisesContainer);
        ExerciseJsonHandler exerciseJsonHandler = new ExerciseJsonHandler();
        List<Exercise> exercises = exerciseJsonHandler.loadExercises(this);

        int i = 0;
        for (Exercise exercise : exercises) {
            i++;
            Button exerciseButton = new Button(this);
            exerciseButton.setText(exercise.getName());
            int finalI = i;
            exerciseButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                intent.putExtra("EXERCISE_ID", String.valueOf(finalI));
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clicked on " + exercise.getName(), Toast.LENGTH_SHORT).show();
            });

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(16, 16, 16, 16);
            exerciseButton.setLayoutParams(layoutParams);
            exerciseButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_button, null));
            exerciseButton.setTextColor(getResources().getColor(android.R.color.white));
            exercisesContainer.addView(exerciseButton);
        }
    }
}

