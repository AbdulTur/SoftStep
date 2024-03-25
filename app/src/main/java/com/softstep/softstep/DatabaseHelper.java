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

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( "
            + EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EXERCISE_NAME + " TEXT NOT NULL, "
            + EXERCISE_DESCRIPTION + " TEXT NOT NULL, " + EXERCISE_INSTRUCTIONS + " TEXT NOT NULL, "
            + EXERCISE_VIDEOPATH + " TEXT NOT NULL, " + EXERCISE_TAGS + " TEXT NOT NULL" + " );";

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
                "Description for Exercise 25", "Description for Exercise 26", "Description for Exercise 27",
                "Description for Exercise 28", "Description for Exercise 29", "Description for Exercise 30"};
        String[] instructions = {"Instructions for Exercise 1", "Instructions for Exercise 2", "Instructions for Exercise 3",
                "Instructions for Exercise 4", "Instructions for Exercise 5", "Instructions for Exercise 6",
                "Instructions for Exercise 7", "Instructions for Exercise 8", "Instructions for Exercise 9",
                "Instructions for Exercise 10", "Instructions for Exercise 11", "Instructions for Exercise 12",
                "Instructions for Exercise 13", "Instructions for Exercise 14", "Instructions for Exercise 15",
                "Instructions for Exercise 16", "Instructions for Exercise 17", "Instructions for Exercise 18",
                "Instructions for Exercise 19", "Instructions for Exercise 20", "Instructions for Exercise 21",
                "Instructions for Exercise 22", "Instructions for Exercise 23", "Instructions for Exercise 24",
                "Instructions for Exercise 25", "Instructions for Exercise 26", "Instructions for Exercise 27",
                "Instructions for Exercise 28", "Instructions for Exercise 29", "Instructions for Exercise 30"};
        String[] videoPaths = {"path/to/video1", "path/to/video2", "path/to/video3", "path/to/video4", "path/to/video5",
                "path/to/video6", "path/to/video7", "path/to/video8", "path/to/video9", "path/to/video10",
                "path/to/video11", "path/to/video12", "path/to/video13", "path/to/video14", "path/to/video15",
                "path/to/video16", "path/to/video17", "path/to/video18", "path/to/video19", "path/to/video20",
                "path/to/video21", "path/to/video22", "path/to/video23", "path/to/video24", "path/to/video25",
                "path/to/video26", "path/to/video27", "path/to/video28", "path/to/video29", "path/to/video30"};
        String[] tags = {"tag1, tag2", "tag3, tag4", "tag5, tag6", "tag7, tag8", "tag9, tag10",
                "tag11, tag12", "tag13, tag14", "tag15, tag16", "tag17, tag18", "tag19, tag20",
                "tag21, tag22", "tag23, tag24", "tag25, tag26", "tag27, tag28", "tag29, tag30",
                "tag31, tag32", "tag33, tag34", "tag35, tag36", "tag37, tag38", "tag39, tag40",
                "tag41, tag42", "tag43, tag44", "tag45, tag46", "tag47, tag48", "tag49, tag50",
                "tag51, tag52", "tag53, tag54", "tag55, tag56", "tag57, tag58", "tag59, tag60"};
        for (int i = 0; i < exerciseNames.length; i++) {
            ContentValues values = new ContentValues();
            values.put(EXERCISE_NAME, exerciseNames[i]);
            values.put(EXERCISE_DESCRIPTION, descriptions[i]);
            values.put(EXERCISE_INSTRUCTIONS, instructions[i]);
            values.put(EXERCISE_VIDEOPATH, videoPaths[i]);
            values.put(EXERCISE_TAGS, tags[i]);
            db.insert(DATABASE_TABLE, null, values);
        }
    }

}
