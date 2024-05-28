package com.softstep.softstep;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class RecommendationEngine {

    private ProfileJsonHandler profileJsonHandler;
    private ExerciseJsonHandler exerciseJsonHandler;
    private DatabaseManager dbManager;

    public RecommendationEngine(Context context) {
        this.profileJsonHandler = new ProfileJsonHandler();
        this.exerciseJsonHandler = new ExerciseJsonHandler();
        this.dbManager = new DatabaseManager(context);
    }

    public void generateAndSaveRecommendations(Context context) {
        ProfileData profileData = profileJsonHandler.loadModifiedProfile(context);
        if (profileData == null) {
            profileData = profileJsonHandler.loadInitialProfile(context);
        }

        List<Exercise> matchedExercises = new ArrayList<>();
        try {
            dbManager.open();
            matchedExercises = dbManager.fetchExercisesByConditions(profileData);
        } catch (android.database.sqlite.SQLiteException e) {
            e.printStackTrace();
        } finally {
        dbManager.close();
    }


        exerciseJsonHandler.saveExercises(context, matchedExercises);
    }
}
