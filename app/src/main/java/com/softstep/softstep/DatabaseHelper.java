package com.softstep.softstep;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "softstep.db";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "EXERCISES";
    static final String EXERCISE_ID = "_ID";
    static final String EXERCISE_NAME = "NAME";
    static final String EXERCISE_DESCRIPTION = "DESCRIPTION";
    static final String EXERCISE_INSTRUCTIONS = "INSTRUCTIONS";
    static final String EXERCISE_VIDEOPATH = "VIDEOPATH";
    static final String EXERCISE_TAGS = "TAGS";

    static final String EXERCISE_IMAGE = "IMAGE";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( "
            + EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXERCISE_NAME + " TEXT NOT NULL, "
            + EXERCISE_DESCRIPTION + " TEXT NOT NULL, " + EXERCISE_INSTRUCTIONS + " TEXT NOT NULL, "
            + EXERCISE_VIDEOPATH + " TEXT NOT NULL, " + EXERCISE_TAGS + " TEXT NOT NULL, "
            + EXERCISE_IMAGE + " TEXT NOT NULL" + " );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_DB_QUERY);
            buildExerciseTable(db);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void buildExerciseTable(SQLiteDatabase db) {
        String[] exerciseNames = {"Walking", "Tai Chi", "Yoga", "Cycling", "Stretching",
                "Swimming", "Dancing", "Chair Yoga", "Standing Marches", "Arm Swings",
                "Hand Grip Exercises", "Balance Exercises", "Leg Raises", "Wall Push-ups", "Handwriting Practice",
                "Finger Tapping", "Neck Stretches", "Leg Swings", "Core Strengthening", "Wall Sit",
                "Heel Raises", "Toe Taps", "Shoulder Rolls", "Deep Breathing", "Box Steps",
                "Leg Circles", "Arm Circles", "Side Leg Raises", "Sit-to-Stand", "Coin Stacking"};
        String[] descriptions = {"Description for Exercise 1", "Description for Exercise 2", "Description for Exercise 3",
                "Description for Exercise 4", "Description for Exercise 5", "Description for Exercise 6",
                "Description for Exercise 7", "Description for Exercise 8", "Description for Exercise 9",
                "Description for Exercise 10", "Description for Exercise 11", "Description for Exercise 12",
                "Description for Exercise 13", "Description for Exercise 14", "Description for Exercise 15",
                "Description for Exercise 16", "Description for Exercise 17", "Description for Exercise 18",
                "Description for Exercise 19", "Description for Exercise 20", "Description for Exercise 21",
                "Description for Exercise 22", "Description for Exercise 23", "Description for Exercise 24",
                "Description for Exercise 25", "Description: Leg circles are a low-impact exercise that involves moving the leg in a circular motion while standing or lying down. This exercise helps improve the range of motion, flexibility, and strength of the hip and leg muscles.", "Description for Exercise 27",
                "Description for Exercise 28", "Description for Exercise 29", "Description for Exercise 30"};
        String[] instructions = {"Instructions for Exercise 1", "Instructions for Exercise 2", "Instructions for Exercise 3",
                "Instructions for Exercise 4", "Instructions for Exercise 5", "Instructions for Exercise 6",
                "Instructions for Exercise 7", "Instructions for Exercise 8", "Instructions for Exercise 9",
                "Instructions for Exercise 10", "Instructions for Exercise 11", "Instructions for Exercise 12",
                "Instructions for Exercise 13", "Instructions for Exercise 14", "Instructions for Exercise 15",
                "Instructions for Exercise 16", "Instructions for Exercise 17", "Instructions for Exercise 18",
                "Instructions for Exercise 19", "Instructions for Exercise 20", "Instructions for Exercise 21",
                "Instructions for Exercise 22", "Instructions for Exercise 23", "Instructions for Exercise 24",
                "Instructions for Exercise 25", "Instructions: \n" + "1. Stand straight with your hands on your hips for balance or hold onto a sturdy chair or wall.\n" +
                "2. Lift one leg slightly off the ground.\n" +
                "3. Move your leg in a circular motion, starting with small circles and gradually increasing the size.\n" +
                "4. Perform 10 circles in one direction, then switch to 10 circles in the opposite direction.\n" +
                "5. Repeat with the other leg.\n" +
                "6. Ensure to maintain a steady breathing pattern throughout the exercise.", "Instructions for Exercise 27",
                "Instructions for Exercise 28", "Instructions for Exercise 29", "Instructions for Exercise 30"};
        String[] videoPaths = {"path/to/video1", "path/to/video2", "path/to/video3", "path/to/video4", "path/to/video5",
                "path/to/video6", "path/to/video7", "path/to/video8", "path/to/video9", "path/to/video10",
                "path/to/video11", "path/to/video12", "path/to/video13", "path/to/video14", "path/to/video15",
                "path/to/video16", "path/to/video17", "path/to/video18", "path/to/video19", "path/to/video20",
                "path/to/video21", "path/to/video22", "path/to/video23", "path/to/video24", "path/to/video25",
                "path/to/video26", "path/to/video27", "path/to/video28", "path/to/video29", "path/to/video30"};
        String[] tags = {
                "stage1,stage2,stage3,bradykinesia,legs,arms,noIssues,reg,no_limitations,flexibility,balance",
                "stage1,stage2,stage3,stage4,bradykinesia,postural_instability,hands,arms,legs,neck,hardStandingUp,reg,occasional,balance,joint,flexibility,muscle",
                "stage1,stage2,stage3,bradykinesia,rigidity,postural_instability,arms,legs,neck,face,hardStandingUp,hardWalk,reg,occasional,rarely,no_limitations,flexibility,muscle,balance_and_coordination",
                "stage1,stage2,bradykinesia,legs,hardWalk,mobilityAids,reg,no_limitations,muscle,endurance",
                "stage1,stage2,stage3,stage4,rigidity,hands,arms,legs,neck,face,noIssues,never,no_limitations,flexibility",
                "stage1,stage2,stage3,postural_instability,arms,legs,neck,noIssues,reg,endurance,muscle,balance_and_coordination",
                "stage1,stage2,bradykinesia,postural_instability,arms,legs,hardStandingUp,occasional,balance,joint,flexibility",
                "stage2,stage3,stage4,rigidity,postural_instability,hands,arms,neck,hardStandingUp,occasional,joint,flexibility",
                "stage1,stage2,bradykinesia,rigidity,legs,noIssues,reg,no_limitations,flexibility,endurance",
                "stage1,stage2,tremors,bradykinesia,rigidityarms,noIssues,reg,no_limitations,flexibility,muscle",
                "stage1,stage2,tremors,bradykinesia,rigidity,hands,noIssues,reg,no_limitations,muscle,flexibility",
                "stage2,stage3,bradykinesia,rigidity,postural_instability,bradykinesia,rigidity,postural_instability,legs,hardStandingUp,reg,balance,flexibility,muscle",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,legs,noIssues,reg,no_limitations,flexibility,muscle,balance",
                "stage1,stage2,tremors,bradykinesia,arms,hardStandingUp,reg,no_limitations,muscle,flexibility,balance",
                "stage1,stage2,tremors,bradykinesia,rigidity,hands,noIssues,rarely,no_limitations,fine_motor_skills,flexibility,muscle",
                "stage1,stage2,stage3,rigidity,hands,noIssues,reg,no_limitations,fine_motor_skills,flexibility",
                "stage1,stage2,stage3,bradykinesia,rigidity,postural_instability,neck,noIssues,occasional,no_limitations,flexibility",
                "stage1,stage2,rigidity,postural_instability,legs,noIssues,reg,no_limitations,flexibility,muscle",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,hardWalk,mobilityAids,occasional,no_limitations,muscle,balance_and_coordination",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,legs,hardStandingUp,reg,endurance,muscle,balance",
                "stage1,stage2,rigidity,postural_instability,legs,noIssues,reg,no_limitations,flexibility,muscle",
                "stage1,stage2,arms,neck,noIssues,reg,no_limitations,flexibility",
                "stage1,stage2,stage3,stage4,stage5,bradykinesia,rigidity,postural_instability,noIssues,reg,rarely,no_limitations,deep_breathing,stress_reduction",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,legs,hardStandingUp,reg,no_limitations,endurance,muscle,balance",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,legs,noIssues,reg,no_limitations,flexibility,muscle",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,arms,noIssues,reg,no_limitations,flexibility",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,legs,hardStandingUp,occasional,no_limitations,flexibility,muscle",
                "stage2,stage3,bradykinesia,rigidity,postural_instability,legs,hardStandingUp,reg,no_limitations,flexibility,muscle,endurance",
                "stage1,stage2,bradykinesia,rigidity,postural_instability,hands,noIssues,rarely,no_limitations,fine_motor_skills",
                "stage1,stage2,tremors,bradykinesia,rigidity,hands,noIssues,rarely,no_limitations,fine_motor_skills"};

        String[] image = {
               "walking_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview",
                "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "arm_swings_removebg_preview",
                "file__1_", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview",
                "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview",
                "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview",
                "legcircles_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview", "other_removebg_preview"};
        for (int i = 0; i < exerciseNames.length; i++) {
            ContentValues values = new ContentValues();
            values.put(EXERCISE_NAME, exerciseNames[i]);
            values.put(EXERCISE_DESCRIPTION, descriptions[i]);
            values.put(EXERCISE_INSTRUCTIONS, instructions[i]);
            values.put(EXERCISE_VIDEOPATH, videoPaths[i]);
            values.put(EXERCISE_TAGS, tags[i]);
            values.put(EXERCISE_IMAGE, image[i]);
            db.insert(DATABASE_TABLE, null, values);
        }
    }

}
