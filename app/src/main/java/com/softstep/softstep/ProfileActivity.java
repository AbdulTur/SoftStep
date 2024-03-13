package com.softstep.softstep;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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
        CheckBox checkBoxTremors = findViewById(R.id.checkBoxTremors);
        CheckBox checkBoxBradykinesia = findViewById(R.id.checkBoxBradykinesia);
        CheckBox checkBoxRigidity = findViewById(R.id.checkBoxRigidity);
        CheckBox checkBoxPosturalInstability = findViewById(R.id.checkBoxPosturalInstability);
        CheckBox checkBoxHands = findViewById(R.id.checkBoxHands);
        CheckBox checkBoxArms = findViewById(R.id.checkBoxArms);
        CheckBox checkBoxLegs = findViewById(R.id.checkBoxLegs);
        CheckBox checkBoxNeck = findViewById(R.id.checkBoxNeck);
        CheckBox checkBoxFace = findViewById(R.id.checkBoxFace);
        RadioGroup mobilityLevelGroup = findViewById(R.id.mobilityLevelGroup);
        RadioGroup exerciseHistoryGroup = findViewById(R.id.exerciseHistoryGroup);
        CheckBox checkBoxNoLimitations = findViewById(R.id.checkBoxNoLimit);
        CheckBox checkBoxEndurance = findViewById(R.id.checkBoxEndurance);
        CheckBox checkBoxBalance = findViewById(R.id.checkBoxBalance);
        CheckBox checkBoxJoint = findViewById(R.id.checkBoxJoint);
        CheckBox checkBoxOther = findViewById(R.id.checkBoxOther);
        CheckBox checkBoxFlex = findViewById(R.id.checkBoxFlex);
        CheckBox checkBoxMuscle = findViewById(R.id.checkBoxMuscle);
        CheckBox checkBoxBalanceAndCoordination = findViewById(R.id.checkBoxBalanceAndCoordination);
        Button submitButton = findViewById(R.id.submitButton);

        // Check the RadioButton according to the loaded ProfileData
        if (stageOfParkinsonsGroup != null) {
            RadioButton selectedStageButton = stageOfParkinsonsGroup.findViewById(profileData.StageOfParkinsons);
            if (selectedStageButton != null) {
                selectedStageButton.setChecked(true);
            }

            // Save the profile when a new stage is selected
            stageOfParkinsonsGroup.setOnCheckedChangeListener((group, checkedId) -> {
                profileData.StageOfParkinsons = checkedId;
                profileJsonHandler.saveProfile(getApplicationContext(), profileData);
                Toast.makeText(getApplicationContext(), "Stage of Parkinson's updated", Toast.LENGTH_SHORT).show();
            });
        }

        // Set up CheckBox listeners
        checkBoxTremors.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[0] = (isChecked) ? checkBoxTremors.getId() : 1);
        checkBoxBradykinesia.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[1] = (isChecked) ?  checkBoxBradykinesia.getId() : 1);
        checkBoxRigidity.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[2] = (isChecked) ?  checkBoxRigidity.getId() : 1);
        checkBoxPosturalInstability.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.PrimaryPhysicalSymptoms[3] = (isChecked) ?  checkBoxPosturalInstability.getId() : 1);
        //Areas Most affected event handlers
        checkBoxHands.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[0] = (isChecked) ?  checkBoxHands.getId() : 1);
        checkBoxArms.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[1] = (isChecked) ?  checkBoxArms.getId() : 1);
        checkBoxLegs.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[2] = (isChecked) ?  checkBoxLegs.getId() : 1);
        checkBoxNeck.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[3] = (isChecked) ?  checkBoxNeck.getId() : 1);
        checkBoxFace.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.AreasMostAffected[4] = (isChecked) ?  checkBoxFace.getId() : 1);
        //Mobility Level Radio group event handler
        mobilityLevelGroup.setOnCheckedChangeListener((mobilityLevelGroup1, i) -> {
            profileData.MobilityLevel = mobilityLevelGroup1.getCheckedRadioButtonId();
            Toast.makeText(getApplicationContext(), String.valueOf(mobilityLevelGroup1.getCheckedRadioButtonId()), Toast.LENGTH_LONG).show();
        });
        //Exercise history radio group event handler
        exerciseHistoryGroup.setOnCheckedChangeListener((exerciseHistoryGroup1, i) -> {
            profileData.ExerciseHistory = exerciseHistoryGroup1.getCheckedRadioButtonId();
            Toast.makeText(getApplicationContext(), String.valueOf(exerciseHistoryGroup1.getCheckedRadioButtonId()), Toast.LENGTH_LONG).show();
        });
        checkBoxNoLimitations.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[0] = (isChecked) ?  checkBoxNoLimitations.getId() : 1);
        checkBoxEndurance.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[1] = (isChecked) ?  checkBoxEndurance.getId() : 1);
        checkBoxBalance.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[2] = (isChecked) ?  checkBoxBalance.getId() : 1);
        checkBoxJoint.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[3] = (isChecked) ?  checkBoxJoint.getId() : 1);
        checkBoxOther.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.MovementLimitations[4] = (isChecked) ?  checkBoxOther.getId() : 1);
        checkBoxFlex.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.Goals[0] = (isChecked) ?  checkBoxFlex.getId() : 1);
        checkBoxMuscle.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.Goals[1] = (isChecked) ?  checkBoxMuscle.getId() : 1);
        checkBoxBalanceAndCoordination.setOnCheckedChangeListener((buttonView, isChecked) -> profileData.Goals[2] = (isChecked) ?  checkBoxBalanceAndCoordination.getId() : 1);
        submitButton.setOnClickListener(v -> {
            profileJsonHandler.saveProfile(this, profileData);
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "submit Button clicked", Toast.LENGTH_SHORT).show();
        });
    }

}

