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

        List<Integer> conditionIds = new ArrayList<>();
        conditionIds.add(profileData.StageOfParkinsons);
        for (int symptom : profileData.PrimaryPhysicalSymptoms) {
            if (symptom != 1) {
                conditionIds.add(symptom);
            }
        }
        for (int area : profileData.AreasMostAffected) {
            if (area != 1) {
                conditionIds.add(area);
            }
        }
        conditionIds.add(profileData.MobilityLevel);
        conditionIds.add(profileData.ExerciseHistory);
        for (int limitation : profileData.MovementLimitations) {
            if (limitation != 1) {
                conditionIds.add(limitation);
            }
        }

        for (int goal : profileData.Goals) {
            if (goal != 1) {
                conditionIds.add(goal);
            }
        }

        List<Exercise> matchedExercises = new ArrayList<>();
        try {
            dbManager.open();
            matchedExercises = dbManager.fetchExercisesByConditions(conditionIds);
        } catch (android.database.sqlite.SQLiteException e) {
            e.printStackTrace();
        } finally {
        dbManager.close();
    }


        exerciseJsonHandler.saveExercises(context, matchedExercises);
    }
}
