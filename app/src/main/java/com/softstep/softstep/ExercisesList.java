package com.softstep.softstep;

import java.util.ArrayList;
import java.util.List;

public class ExercisesList {
    private List<Exercise> exercises;


    public ExercisesList() {
        this.exercises = new ArrayList<>();
    }


    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }


    public boolean removeExercise(Exercise exercise) {
        return exercises.remove(exercise);
    }


    public List<Exercise> getExercises() {
        return exercises;
    }
}

