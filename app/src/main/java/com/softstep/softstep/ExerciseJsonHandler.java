package com.softstep.softstep;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ExerciseJsonHandler {

    private Gson gson;

    public ExerciseJsonHandler() {
        this.gson = new Gson();
    }

    public List<Exercise> readExercisesFromFile(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            Type exerciseListType = new TypeToken<List<Exercise>>() {}.getType();
            return gson.fromJson(reader, exerciseListType);
        }
    }

    public void writeExercisesToFile(List<Exercise> exercises, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(exercises, writer);
        }
    }
}
