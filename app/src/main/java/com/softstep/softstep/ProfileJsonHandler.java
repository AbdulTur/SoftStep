package com.softstep.softstep;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class ProfileJsonHandler {
    private static final String JSON_FILE_PATH = "UserProfile.json";
    private final Gson gson = new Gson();

    // Method to load initial profile from assets
    public ProfileData loadInitialProfile(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(JSON_FILE_PATH);
            Reader reader = new InputStreamReader(is);
            ProfileData profileData = gson.fromJson(reader, ProfileData.class);
            reader.close();
            return profileData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ProfileData(); // Return a new instance with default values if loading fails
    }

    // Method to save modified profile to internal storage
    public void saveProfile(Context context, ProfileData profileData) {
        try {
            FileOutputStream fos = context.openFileOutput(JSON_FILE_PATH, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            gson.toJson(profileData, outputStreamWriter);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load modified profile from internal storage
    public ProfileData loadModifiedProfile(Context context) {
        File file = new File(context.getFilesDir(), JSON_FILE_PATH);
        if (file.exists()) {
            try {
                FileInputStream fis = context.openFileInput(JSON_FILE_PATH);
                InputStreamReader isr = new InputStreamReader(fis);
                ProfileData profileData = gson.fromJson(isr, ProfileData.class);
                isr.close();
                return profileData;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null; // Return null if no modified profile is found
    }
}
