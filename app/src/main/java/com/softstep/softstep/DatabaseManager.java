package com.softstep.softstep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import android.database.sqlite.SQLiteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

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

//    public List<Exercise> fetchExercisesByConditions(List<Integer> conditionIds) {
//        List<Exercise> matchedExercises = new ArrayList<>();
//        for (Integer conditionId : conditionIds) {
//            String selection = DatabaseHelper.EXERCISE_TAGS + " LIKE ?";
//            String[] selectionArgs = new String[]{"%" + conditionId + "%"};
//            Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, null, selection, selectionArgs, null, null, null);
//
//            while (cursor.moveToNext()) {
//                int idIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_ID);
//                int nameIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_NAME);
//                int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_DESCRIPTION);
//                int instructionsIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_INSTRUCTIONS);
//                int videoPathIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_VIDEOPATH);
//                int tagsIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_TAGS);
//
//                if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && instructionsIndex != -1 && videoPathIndex != -1 && tagsIndex != -1) {
//                    int currentId = cursor.getInt(idIndex);
//                    boolean isDuplicate = false;
//
//                    for (Exercise existingExercise : matchedExercises) {
//                        if (existingExercise.getId() == currentId) {
//                            isDuplicate = true;
//                            break;
//                        }
//                    }
//
//                    if (!isDuplicate) {
//                        Exercise exercise = new Exercise(
//                                cursor.getInt(idIndex),
//                                cursor.getString(nameIndex),
//                                cursor.getString(descriptionIndex),
//                                cursor.getString(instructionsIndex),
//                                cursor.getString(videoPathIndex),
//                                cursor.getString(tagsIndex)
//                        );
//                        matchedExercises.add(exercise);
//                    }
//                }
//            }
//            cursor.close();
//        }
//        return matchedExercises;
//    }

    private static void removeValueFromList(List<String> list, String value) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(value)) {
                iterator.remove();
            }
        }
    }
    private Exercise createExerciseFromCursor(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_ID);
        int nameIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_NAME);
        int descriptionIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_DESCRIPTION);
        int instructionsIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_INSTRUCTIONS);
        int videoPathIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_VIDEOPATH);
        int tagsIndex = cursor.getColumnIndex(DatabaseHelper.EXERCISE_TAGS);

        if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && instructionsIndex != -1 && videoPathIndex != -1 && tagsIndex != -1) {
            int id = cursor.getInt(idIndex);
            String name = cursor.getString(nameIndex);
            String description = cursor.getString(descriptionIndex);
            String instructions = cursor.getString(instructionsIndex);
            String videoPath = cursor.getString(videoPathIndex);
            String tags = cursor.getString(tagsIndex);

            return new Exercise(id, name, description, instructions, videoPath, tags);
        }
        return null;
    }
    private boolean containsAnySymptom(String tags, List<String> symptoms) {
        for (String symptom : symptoms) {
            if (tags.contains(symptom)) {
                return true;
            }
        }
        return false;
    }

    public void filterExercisesBySymptoms(List<String> primaryPhysicalSymptoms, List<Exercise> matchedExercises) {
        Iterator<Exercise> iterator = matchedExercises.iterator();
        while (iterator.hasNext()) {
            Exercise exercise = iterator.next();
            if (!containsAnySymptom(exercise.getTags(), primaryPhysicalSymptoms)) {
                iterator.remove();  // Remove the exercise if no symptoms match
            }
        }
    }

    public void filterExercisesByMobilityLevel(String mobilityLevel, List<Exercise> matchedExercises) {
        Iterator<Exercise> iterator = matchedExercises.iterator();
        while (iterator.hasNext()) {
            Exercise exercise = iterator.next();
            if (!exercise.getTags().contains(mobilityLevel)) {
                iterator.remove();
            }
        }
    }





    public List<Exercise> fetchExercisesByConditions(ProfileData profileData) {

        String stage = profileData.StageOfParkinsons;
//        List<String> PrimaryPhysicalSymptoms = Arrays.asList(profileData.PrimaryPhysicalSymptoms);
//        List<String> area = Arrays.asList(profileData.AreasMostAffected);
//        List<String> MovementLimitations = Arrays.asList(profileData.MovementLimitations);
//        List<String> Goals = Arrays.asList(profileData.Goals);
        String MobilityLevel = profileData.MobilityLevel;
        String ExerciseHistory = profileData.ExerciseHistory;

        List<String> PrimaryPhysicalSymptoms = new ArrayList<>(Arrays.asList(profileData.PrimaryPhysicalSymptoms));
        List<String> area = new ArrayList<>(Arrays.asList(profileData.AreasMostAffected));
        List<String> MovementLimitations = new ArrayList<>(Arrays.asList(profileData.MovementLimitations));
        List<String> Goals = new ArrayList<>(Arrays.asList(profileData.Goals));

        removeValueFromList(PrimaryPhysicalSymptoms, "1");
        removeValueFromList(area, "1");
        removeValueFromList(MovementLimitations, "1");
        removeValueFromList(Goals, "1");

        List<Exercise> matchedExercises = new ArrayList<>();

        String selection = DatabaseHelper.EXERCISE_TAGS + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + stage + "%"};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, null, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            Exercise exercise = createExerciseFromCursor(cursor);
            if (exercise != null) {
                matchedExercises.add(exercise);
            }
        }
        cursor.close();

        filterExercisesBySymptoms(PrimaryPhysicalSymptoms, matchedExercises);
        filterExercisesBySymptoms(area, matchedExercises);
        filterExercisesBySymptoms(MovementLimitations, matchedExercises);
        filterExercisesBySymptoms(Goals, matchedExercises);
        filterExercisesByMobilityLevel(MobilityLevel, matchedExercises);
        filterExercisesByMobilityLevel(ExerciseHistory, matchedExercises);

        return matchedExercises;
    }
}
