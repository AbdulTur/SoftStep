package com.softstep.softstep;

public class ProfileData {

    public ProfileData() {
        // No-argument constructor required for Gson
    }

    public String Name = "";
    public int StageOfParkinsons = 1;
    public int[] PrimaryPhysicalSymptoms = new int[]{1, 1, 1, 1, 1};;
    public int[] AreasMostAffected = new int[]{1, 1, 1, 1, 1};
    public int MobilityLevel = 1;
    public int ExerciseHistory = 1;
    public int[] MovementLimitations = new int[]{1, 1, 1, 1, 1};;
    public int[] Goals = new int[]{1, 1, 1};;
}
