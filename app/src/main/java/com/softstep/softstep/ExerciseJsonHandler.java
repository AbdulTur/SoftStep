package com.softstep.softstep;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExerciseJsonHandler {

    private static final String JSON_FILE_NAME = "Exercises.Json";
    private final Gson gson = new Gson();


    public ExerciseJsonHandler() {
    }


    public List<Exercise> loadInitialExercises(Context context) {
        try  {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(JSON_FILE_NAME);
            Reader reader = new InputStreamReader(is);
            Type exerciseListType = new TypeToken<List<Exercise>>() {}.getType();
            return gson.fromJson(reader, exerciseListType);
        } catch (IOException e) {

            return new ArrayList<>();
        }
    }

    public void saveExercises(Context context, List<Exercise> exercises) {
        try (FileOutputStream fos = context.openFileOutput(JSON_FILE_NAME, Context.MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos)) {
            gson.toJson(exercises, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Exercise> loadExercises(Context context) {
        File file = new File(context.getFilesDir(), JSON_FILE_NAME);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(fis)) {
                Type exerciseListType = new TypeToken<List<Exercise>>() {}.getType();
                return gson.fromJson(isr, exerciseListType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
