package com.softstep.softstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import com.google.android.material.chip.Chip;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ProfileJsonHandler profileJsonHandler = new ProfileJsonHandler();
    private ProfileData profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Load the initial profile from assets, then check for any modified profile in internal storage
        profileData = profileJsonHandler.loadInitialProfile(this);
        ProfileData modifiedData = profileJsonHandler.loadModifiedProfile(this);
        if (modifiedData != null) {
            profileData = modifiedData;
        }

        // Initialize UI components
        RadioGroup stageOfParkinsonsGroup = findViewById(R.id.stageOfParkinsonsGroup);
        Chip checkBoxTremors = findViewById(R.id.checkBoxTremors);
        Chip checkBoxBradykinesia = findViewById(R.id.checkBoxBradykinesia);
        Chip checkBoxRigidity = findViewById(R.id.checkBoxRigidity);
        Chip checkBoxPosturalInstability = findViewById(R.id.checkBoxPosturalInstability);
        Chip checkBoxHands = findViewById(R.id.checkBoxHands);
        Chip checkBoxArms = findViewById(R.id.checkBoxArms);
        Chip checkBoxLegs = findViewById(R.id.checkBoxLegs);
        Chip checkBoxNeck = findViewById(R.id.checkBoxNeck);
        Chip checkBoxFace = findViewById(R.id.checkBoxFace);
        RadioGroup mobilityLevelGroup = findViewById(R.id.mobilityLevelGroup);
        RadioGroup exerciseHistoryGroup = findViewById(R.id.exerciseHistoryGroup);
        Chip checkBoxNoLimitations = findViewById(R.id.checkBoxNoLimit);
        Chip checkBoxEndurance = findViewById(R.id.checkBoxEndurance);
        Chip checkBoxBalance = findViewById(R.id.checkBoxBalance);
        Chip checkBoxJoint = findViewById(R.id.checkBoxJoint);
        Chip checkBoxOther = findViewById(R.id.checkBoxOther);
        Chip checkBoxFlex = findViewById(R.id.checkBoxFlex);
        Chip checkBoxMuscle = findViewById(R.id.checkBoxMuscle);
        Chip checkBoxBalanceAndCoordination = findViewById(R.id.checkBoxBalanceAndCoordination);
        Button submitButton = findViewById(R.id.submitButton);
        ImageButton homeButton = findViewById(R.id.homeButton);

        // Check the RadioButton according to the loaded ProfileData

        int selectedStageId =0;

        switch (profileData.StageOfParkinsons) {
            case "stage1":
                selectedStageId = R.id.stage1;
                break;
            case "stage2":
                selectedStageId = R.id.stage2;
                break;
            case "stage3":
                selectedStageId = R.id.stage3;
                break;
            case "stage4":
                selectedStageId = R.id.stage4;
                break;
            case "stage5":
                selectedStageId = R.id.stage5;
                break;
        }

        RadioButton selectedStageButton = stageOfParkinsonsGroup.findViewById(selectedStageId);
        if (selectedStageButton != null) {
            selectedStageButton.setChecked(true);
        }
        if ("tremors".equals(profileData.PrimaryPhysicalSymptoms[0])) {
            checkBoxTremors.setChecked(true);
        }
        if ("bradykinesia".equals(profileData.PrimaryPhysicalSymptoms[1])) {
            checkBoxBradykinesia.setChecked(true);
        }
        if ("rigidity".equals(profileData.PrimaryPhysicalSymptoms[2])) {
            checkBoxRigidity.setChecked(true);
        }
        if ("postural_instability".equals(profileData.PrimaryPhysicalSymptoms[3])) {
            checkBoxPosturalInstability.setChecked(true);
        }

        if ("hands".equals(profileData.AreasMostAffected[0])) {
            checkBoxHands.setChecked(true);
        }
        if ("arms".equals(profileData.AreasMostAffected[1])) {
            checkBoxArms.setChecked(true);
        }
        if ("legs".equals(profileData.AreasMostAffected[2])) {
            checkBoxLegs.setChecked(true);
        }
        if ("neck".equals(profileData.AreasMostAffected[3])) {
            checkBoxNeck.setChecked(true);
        }
        if ("face".equals(profileData.AreasMostAffected[4])) {
            checkBoxFace.setChecked(true);
        }

        int selectedMobilityLevelId =0;

        switch (profileData.MobilityLevel) {
            case "noIssues":
                selectedMobilityLevelId = R.id.noIssues;
                break;
            case "hardStandingUp":
                selectedMobilityLevelId = R.id.hardStandingUp;
                break;
            case "hardWalk":
                selectedMobilityLevelId = R.id.hardWalk;
                break;
            case "mobilityAids":
                selectedMobilityLevelId = R.id.mobilityAids;
                break;
            case "wheelchairBound":
                selectedMobilityLevelId = R.id.wheelchairBound;
                break;
        }

        RadioButton selectedMobilityLevelButton = mobilityLevelGroup.findViewById(selectedMobilityLevelId);
        if (selectedMobilityLevelButton != null) {
            selectedMobilityLevelButton.setChecked(true);
        }

        int selectedExerciseHistoryId =0;

        switch (profileData.ExerciseHistory) {
            case "reg":
                selectedExerciseHistoryId = R.id.reg;
                break;
            case "occasional":
                selectedExerciseHistoryId = R.id.occasional;
                break;
            case "rarely":
                selectedExerciseHistoryId = R.id.rarely;
                break;
            case "never":
                selectedExerciseHistoryId = R.id.never;
                break;
        }

        RadioButton selectedExerciseHistoryButton = exerciseHistoryGroup.findViewById(selectedExerciseHistoryId);
        if (selectedExerciseHistoryButton != null) {
            selectedExerciseHistoryButton.setChecked(true);
        }
        if ("no_limitations".equals(profileData.MovementLimitations[0])) {
            checkBoxNoLimitations.setChecked(true);
        }
        if ("endurance".equals(profileData.MovementLimitations[1])) {
            checkBoxEndurance.setChecked(true);
        }
        if ("balance".equals(profileData.MovementLimitations[2])) {
            checkBoxBalance.setChecked(true);
        }
        if ("joint".equals(profileData.MovementLimitations[3])) {
            checkBoxJoint.setChecked(true);
        }
        if ("other".equals(profileData.MovementLimitations[4])) {
            checkBoxOther.setChecked(true);
        }
        if ("flexibility".equals(profileData.Goals[0])) {
            checkBoxFlex.setChecked(true);
        }
        if ("muscle".equals(profileData.Goals[1])) {
            checkBoxMuscle.setChecked(true);
        }
        if ("balance_and_coordination".equals(profileData.Goals[2])) {
            checkBoxBalanceAndCoordination.setChecked(true);
        }

        stageOfParkinsonsGroup.setOnCheckedChangeListener((stageOfParkinsonsGroup1, i) -> {
            String stageTemp = "1";
            if (stageOfParkinsonsGroup1.getCheckedRadioButtonId() == R.id.stage1) {
                stageTemp = "stage1";
            } else if (stageOfParkinsonsGroup1.getCheckedRadioButtonId() == R.id.stage2) {
                stageTemp = "stage2";
            } else if (stageOfParkinsonsGroup1.getCheckedRadioButtonId() == R.id.stage3) {
                stageTemp = "stage3";
            } else if (stageOfParkinsonsGroup1.getCheckedRadioButtonId() == R.id.stage4) {
                stageTemp = "stage4";
            } else if (stageOfParkinsonsGroup1.getCheckedRadioButtonId() == R.id.stage5) {
                stageTemp = "stage5";
            }
            profileData.StageOfParkinsons = stageTemp;
            Toast.makeText(getApplicationContext(), "Stage of Parkinson's updated", Toast.LENGTH_SHORT).show();
        });
        // Set up CheckBox listeners
        checkBoxTremors.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[0] = (isChecked) ? "tremors" : "1");
        checkBoxBradykinesia.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[1] = (isChecked) ?  "bradykinesia" : "1");
        checkBoxRigidity.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[2] = (isChecked) ?  "rigidity" : "1");
        checkBoxPosturalInstability.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[3] = (isChecked) ?  "postural_instability" : "1");
        //Areas Most affected event handlers
        checkBoxHands.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[0] = (isChecked) ?  "hands" : "1");
        checkBoxArms.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[1] = (isChecked) ?  "arms" : "1");
        checkBoxLegs.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[2] = (isChecked) ?  "legs": "1");
        checkBoxNeck.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[3] = (isChecked) ?  "neck" : "1");
        checkBoxFace.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[4] = (isChecked) ?  "face" : "1");
        //Mobility Level Radio group event handler
        mobilityLevelGroup.setOnCheckedChangeListener((mobilityLevelGroup1, i) -> {
            String mobilityTemp = "1";
            if (mobilityLevelGroup1.getCheckedRadioButtonId() == R.id.noIssues) {
                mobilityTemp = "noIssues";
            } else if (mobilityLevelGroup1.getCheckedRadioButtonId() == R.id.hardStandingUp) {
                mobilityTemp = "hardStandingUp";
            } else if (mobilityLevelGroup1.getCheckedRadioButtonId() == R.id.hardWalk) {
                mobilityTemp = "hardWalk";
            } else if (mobilityLevelGroup1.getCheckedRadioButtonId() == R.id.mobilityAids) {
                mobilityTemp = "mobilityAids";
            } else if (mobilityLevelGroup1.getCheckedRadioButtonId() == R.id.wheelchairBound) {
                mobilityTemp = "wheelchairBound";
            }
            profileData.MobilityLevel = mobilityTemp;
            Toast.makeText(getApplicationContext(), String.valueOf(mobilityLevelGroup1.getCheckedRadioButtonId()), Toast.LENGTH_LONG).show();
        });
        //Exercise history radio group event handler
        exerciseHistoryGroup.setOnCheckedChangeListener((exerciseHistoryGroup1, i) -> {
            String historyTemp = "1";
            if (exerciseHistoryGroup1.getCheckedRadioButtonId() == R.id.reg) {
                historyTemp = "reg";
            } else if (exerciseHistoryGroup1.getCheckedRadioButtonId() == R.id.occasional) {
                historyTemp = "occasional";
            } else if (exerciseHistoryGroup1.getCheckedRadioButtonId() == R.id.rarely) {
                historyTemp = "rarely";
            } else if (exerciseHistoryGroup1.getCheckedRadioButtonId() == R.id.never) {
                historyTemp = "never";
            }
            profileData.ExerciseHistory = historyTemp;
            Toast.makeText(getApplicationContext(), String.valueOf(exerciseHistoryGroup1.getCheckedRadioButtonId()), Toast.LENGTH_LONG).show();
        });
        checkBoxNoLimitations.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[0] = (isChecked) ?  "no_limitations" : "1");
        checkBoxEndurance.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[1] = (isChecked) ?  "endurance" : "1");
        checkBoxBalance.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[2] = (isChecked) ?  "balance" : "1");
        checkBoxJoint.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[3] = (isChecked) ?  "joint" : "1");
        checkBoxOther.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[4] = (isChecked) ?  "other" : "1");
        checkBoxFlex.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.Goals[0] = (isChecked) ?  "flexibility" : "1");
        checkBoxMuscle.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.Goals[1] = (isChecked) ?  "muscle" : "1");
        checkBoxBalanceAndCoordination.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.Goals[2] = (isChecked) ?  "balance_and_coordination" : "1");

        submitButton.setOnClickListener(v -> {
            //checking for any missing/incomplete fields
            if (profileData.StageOfParkinsons.equals("1") || (profileData.PrimaryPhysicalSymptoms[0].equals("1") && profileData.PrimaryPhysicalSymptoms[1].equals("1") && profileData.PrimaryPhysicalSymptoms[2].equals("1") && profileData.PrimaryPhysicalSymptoms[3].equals("1")) || (profileData.AreasMostAffected[0].equals("1") && profileData.AreasMostAffected[1].equals("1") && profileData.AreasMostAffected[2].equals("1") && profileData.AreasMostAffected[3].equals("1") && profileData.AreasMostAffected[4].equals("1")) || (profileData.MobilityLevel.equals("1")) || (profileData.ExerciseHistory.equals("1")) || (profileData.MovementLimitations[0].equals("1") && profileData.MovementLimitations[1].equals("1") && profileData.MovementLimitations[2].equals("1") && profileData.MovementLimitations[3].equals("1") && profileData.MovementLimitations[4].equals("1")) || (profileData.Goals[0].equals("1") && profileData.Goals[1].equals("1") && profileData.Goals[2].equals("1"))){
                Toast.makeText(getApplicationContext(), "Error: Incomplete/Missing Fields", Toast.LENGTH_LONG).show();
            }
            else {
                profileJsonHandler.saveProfile(this, profileData);
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "submit Button clicked", Toast.LENGTH_SHORT).show();
                RecommendationEngine recommendationEngine = new RecommendationEngine(getApplicationContext());
                recommendationEngine.generateAndSaveRecommendations(getApplicationContext());
            }
        });
        homeButton.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "home Button clicked", Toast.LENGTH_SHORT).show();
        });

        ImageButton settingsButton = findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, SettingsActivity.class));
            Toast.makeText(getApplicationContext(), "Settings Button was clicked", Toast.LENGTH_SHORT).show();
        });
    }

}

