package com.softstep.softstep;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class ProfileJsonHandler {
    private static String JSON_FILE_PATH = "UserProfile.json";
    private Gson gson;

    public Context context;

    public ProfileJsonHandler() {
        gson = new Gson();
    }

    // Method to gather user information and save it to JSON file
    public void saveProfile(ProfileData profileData) {
        try (FileWriter writer = new FileWriter(new File(context.getFilesDir(), JSON_FILE_PATH))) {
            gson.toJson(profileData, writer);
            System.out.println("Profile saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read user information from JSON file
    public ProfileData loadProfile(Context context) {
        try (Reader reader = new FileReader(new File(context.getFilesDir(), JSON_FILE_PATH))) {
            ProfileData profileData = gson.fromJson(reader, ProfileData.class);
            System.out.println("Profile loaded successfully.");
            return profileData;
        } catch (FileNotFoundException e) {
            System.out.println("Profile file not found. Creating new profile.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
