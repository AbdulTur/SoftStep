package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private ExerciseJsonHandler exerciseJsonHandler = new ExerciseJsonHandler();
    private List<Exercise> exercisesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        String message = intent.getStringExtra("EXERCISE_ID");

        exercisesList = exerciseJsonHandler.loadInitialExercises(this);
        List<Exercise> modifiedList = exerciseJsonHandler.loadExercises(this);
        if (modifiedList != null) {
            exercisesList = modifiedList;
        }

        Exercise exercise = exercisesList.get(Integer.valueOf(message) - 1);

        TextView titleTV = (TextView) findViewById(R.id.exerciseTitleText);
        titleTV.setText(exercise.getName());

        TextView descriptionTV = (TextView) findViewById(R.id.exerciseDescription1);
        descriptionTV.setText(exercise.getDescription());

        TextView instructionsTV = (TextView) findViewById(R.id.exerciseInstructions1);
        instructionsTV.setText((exercise.getInstructions()));

        WebView videoView = findViewById(R.id.videoView);
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cRLB7WqX0fU?si=67iv4nzlgIiGpucl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        videoView.loadData(video,  "text/html", "utf-8");
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.setWebChromeClient(new WebChromeClient());

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(ExerciseActivity.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "Back Button was clicked", Toast.LENGTH_SHORT).show();
        });

    }
}