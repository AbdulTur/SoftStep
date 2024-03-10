package com.softstep.softstep;
import static java.security.AccessController.getContext;

import android.os.Bundle;
import org.json.*;

import android.provider.ContactsContract;
import android.widget.CheckBox;
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

        RadioGroup stageOfParkinsonsGroup = findViewById(R.id.stageOfParkinsonsGroup);
        CheckBox checkBoxTremors = findViewById(R.id.checkBoxTremors);
        stageOfParkinsonsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup stageOfParkinsonsGroup, int i) {
                RadioButton rb = findViewById(i);
                profileData.StageOfParkinsons = stageOfParkinsonsGroup.getCheckedRadioButtonId();
                ProfileData testpd = profileJsonHandler.loadProfile(getBaseContext());
                System.out.println(testpd.StageOfParkinsons);
                profileJsonHandler.context = getBaseContext();
                profileJsonHandler.saveProfile(profileData);
                Toast.makeText(getApplicationContext(), String.valueOf(stageOfParkinsonsGroup.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();


            } 
        });
    }
}
