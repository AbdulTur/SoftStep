package com.softstep.softstep;

public class ProfileData {

    public ProfileData() {
        // No-argument constructor required for Gson
    }

    public String Name = "";
    public int StageOfParkinsons = 1;
    public String[] PrimaryPhysicalSymptoms = new String[]{"", "", "", "", ""};;
    public String[] AreasMostAffected = new String[]{"", "", "", ""};;
    public int MobilityLevel = 1;
    public int ExerciseHistory = 1;
    public String[] MovementLimitations = new String[]{"", "", "", ""};;
    public String[] Goals = new String[]{"", "", ""};;
}
