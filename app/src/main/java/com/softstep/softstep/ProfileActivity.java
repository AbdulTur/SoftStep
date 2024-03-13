package com.softstep.softstep;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        setupCheckBoxListener(checkBoxTremors, 0); // Passing index 0 for PrimaryPhysicalSymptoms[0]
        setupCheckBoxListener(checkBoxBradykinesia, 1); // Similarly, pass the appropriate index for each symptom
        // Continue setting up listeners for other CheckBoxes...

        // Example of setting up a listener for checkBoxTremors
        // Repeat for other CheckBoxes with the respective indices
    }

    private void setupCheckBoxListener(CheckBox checkBox, int symptomIndex) {
        if (checkBox != null) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                profileData.PrimaryPhysicalSymptoms[symptomIndex] = isChecked ? checkBox.getId() : 1;
                profileJsonHandler.saveProfile(getApplicationContext(), profileData);
                Toast.makeText(getApplicationContext(), checkBox.getText() + " updated", Toast.LENGTH_SHORT).show();
            });
        }
    }
}

