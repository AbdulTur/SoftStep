package com.softstep.softstep;
import static java.security.AccessController.getContext;

import android.os.Bundle;
import org.json.*;

import android.provider.ContactsContract;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ProfileActivity extends AppCompatActivity {

    private ProfileJsonHandler profileJsonHandler = new ProfileJsonHandler();
    private ProfileData profileData = new ProfileData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Ensure to create this layout in res/layout

        profileJsonHandler.context = getBaseContext();
        profileData = profileJsonHandler.loadProfile(getBaseContext());

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

        RadioButton radioButton = findViewById(profileData.StageOfParkinsons);
        radioButton.setChecked(true);

        stageOfParkinsonsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup stageOfParkinsonsGroup, int i) {
                RadioButton rb = findViewById(i);
                profileData.StageOfParkinsons = stageOfParkinsonsGroup.getCheckedRadioButtonId();
                System.out.println(profileData.StageOfParkinsons);
                profileJsonHandler.saveProfile(profileData);
                Toast.makeText(getApplicationContext(), String.valueOf(stageOfParkinsonsGroup.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
            } 
        });

        checkBoxTremors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.PrimaryPhysicalSymptoms[0] = (isChecked) ? checkBoxTremors.getId() : 1;
                System.out.println(profileData.PrimaryPhysicalSymptoms[0]);
            }
        });
        checkBoxBradykinesia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.PrimaryPhysicalSymptoms[1] = (isChecked) ?  checkBoxBradykinesia.getId() : 1;
                System.out.println(profileData.PrimaryPhysicalSymptoms[1]);
            }
        });
        checkBoxRigidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.PrimaryPhysicalSymptoms[2] = (isChecked) ?  checkBoxRigidity.getId() : 1;
            }
        });
        checkBoxPosturalInstability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.PrimaryPhysicalSymptoms[3] = (isChecked) ?  checkBoxPosturalInstability.getId() : 1;
            }
        });
        //Areas Most affected event handlers
        checkBoxHands.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.AreasMostAffected[0] = (isChecked) ?  checkBoxHands.getId() : 1;
            }
        });
        checkBoxArms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.AreasMostAffected[1] = (isChecked) ?  checkBoxArms.getId() : 1;
            }
        });
        checkBoxLegs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.AreasMostAffected[2] = (isChecked) ?  checkBoxLegs.getId() : 1;
            }
        });
        checkBoxNeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.AreasMostAffected[3] = (isChecked) ?  checkBoxNeck.getId() : 1;
            }
        });
        checkBoxFace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.AreasMostAffected[4] = (isChecked) ?  checkBoxFace.getId() : 1;
            }
        });
        /*
        //Mobility Level Radio group event handler
        mobilityLevelGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup mobilityLevelGroup, int i) {
                profileData.StageOfParkinsons = mobilityLevelGroup.getCheckedRadioButtonId();

                ProfileData testpd = profileJsonHandler.loadProfile(getBaseContext());
                System.out.println(testpd.MobilityLevel);
                profileJsonHandler.context = getBaseContext();
                profileJsonHandler.saveProfile(profileData);

                Toast.makeText(getApplicationContext(), String.valueOf(mobilityLevelGroup.getCheckedRadioButtonId()), Toast.LENGTH_LONG).show();
            }
        });
        //Exercise history radio group event handler
        exerciseHistoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup exerciseHistoryGroup, int i) {
                profileData.StageOfParkinsons = exerciseHistoryGroup.getCheckedRadioButtonId();

                ProfileData testpd = profileJsonHandler.loadProfile(getBaseContext());
                System.out.println(testpd.MobilityLevel);
                profileJsonHandler.context = getBaseContext();
                profileJsonHandler.saveProfile(profileData);

                Toast.makeText(getApplicationContext(), String.valueOf(exerciseHistoryGroup.getCheckedRadioButtonId()), Toast.LENGTH_LONG).show();
            }
        });
        checkBoxNoLimitations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.MovementLimitations[0] = (isChecked) ?  checkBoxNoLimitations.getId() : 1;
            }
        });
        checkBoxEndurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.MovementLimitations[1] = (isChecked) ?  checkBoxEndurance.getId() : 1;
            }
        });
        checkBoxBalance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.MovementLimitations[2] = (isChecked) ?  checkBoxBalance.getId() : 1;
            }
        });
        checkBoxJoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                profileData.MovementLimitations[2] = (isChecked) ?  checkBoxJoint.getId() : 1;
            }
        });
         */
    }
}
