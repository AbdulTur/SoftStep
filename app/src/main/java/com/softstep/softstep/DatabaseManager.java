package com.softstep.softstep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import android.database.sqlite.SQLiteException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context _context) {
        context = _context;
    }

    public DatabaseManager open() throws SQLiteException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert (String name, String description, String instructions, String videopath, String tags){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EXERCISE_NAME, name);
        contentValues.put(DatabaseHelper.EXERCISE_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.EXERCISE_INSTRUCTIONS, instructions);
        contentValues.put(DatabaseHelper.EXERCISE_VIDEOPATH, videopath);
        contentValues.put(DatabaseHelper.EXERCISE_TAGS, tags);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String [] columns = new String[] {DatabaseHelper.EXERCISE_ID, DatabaseHelper.EXERCISE_NAME, DatabaseHelper.EXERCISE_DESCRIPTION, DatabaseHelper.EXERCISE_INSTRUCTIONS, DatabaseHelper.EXERCISE_VIDEOPATH, DatabaseHelper.EXERCISE_TAGS };
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public List<Exercise> fetchExercisesByConditions(List<Integer> conditionIds) {
        List<Exercise> matchedExercises = new ArrayList<>();
        for (Integer conditionId : conditionIds) {
            String selection = DatabaseHelper.EXERCISE_TAGS + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + conditionId + "%"};
            Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, null, selection, selectionArgs, null, null, null);

            while (cursor.moveToNext()) {

                int idIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_ID);
                int nameIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_NAME);
                int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_DESCRIPTION);
                int instructionsIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_INSTRUCTIONS);
                int videoPathIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_VIDEOPATH);
                int tagsIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_TAGS);

                if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && instructionsIndex != -1 && videoPathIndex != -1 && tagsIndex != -1) {
                    Exercise exercise = new Exercise(
                            cursor.getInt(idIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(descriptionIndex),
                            cursor.getString(instructionsIndex),
                            cursor.getString(videoPathIndex),
                            cursor.getString(tagsIndex)
                    );
                    if (!matchedExercises.contains(exercise)) {
                        matchedExercises.add(exercise);
                    }
                }
            }
            cursor.close();
        }
        return matchedExercises;
    }


}
