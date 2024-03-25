package com.softstep.softstep;

import android.content.Context;
import android.util.Log;
import java.util.List;

public class RecommendationEngine {

    private ExerciseJsonHandler exerciseJsonHandler;
    private Context context; // Assuming this is provided, e.g., from an Activity or Application

    public RecommendationEngine(Context context) {
        this.context = context;
        this.exerciseJsonHandler = new ExerciseJsonHandler();
    }

    public void testLoadInitialExercises() {
        List<Exercise> exercises = exerciseJsonHandler.loadInitialExercises(context);
        for (Exercise exercise : exercises) {
            Log.d("TestLoadInitial", "Exercise: " + exercise.getName());
        }
    }

    public void testSaveExercises() {
        List<Exercise> exercises = exerciseJsonHandler.loadInitialExercises(context);
        // Modify the list or add new exercises as needed for testing

        exerciseJsonHandler.saveExercises(context, exercises);
        Log.d("TestSaveExercises", "Exercises saved to internal storage.");
    }

    public void testLoadExercises() {
        List<Exercise> exercises = exerciseJsonHandler.loadExercises(context);
        if (exercises != null) {
            for (Exercise exercise : exercises) {
                Log.d("TestLoadExercises", "Exercise: " + exercise.getName());
            }
        } else {
            Log.d("TestLoadExercises", "No exercises loaded from internal storage.");
        }
    }
}
