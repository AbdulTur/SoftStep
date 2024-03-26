package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    private Button timePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        timePickerButton = findViewById(R.id.timePickerButton);

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
            Toast.makeText(getApplicationContext(), "Back Button was clicked", Toast.LENGTH_SHORT).show();
        });

    }

    private void showTimePickerDialog() {
        // Get current time
        final Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        // Create a time picker dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Handle time selection
                        String time = selectedHour + ":" + selectedMinute;
                        //Toast.makeText(this, "Selected time: " + time, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, true);

        // Show the dialog
        timePickerDialog.show();
    }
}