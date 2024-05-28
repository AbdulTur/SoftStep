package com.softstep.softstep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            Toast.makeText(getApplicationContext(), "Profile Button was clicked", Toast.LENGTH_SHORT).show();
        });

        LinearLayout exercisesContainer = findViewById(R.id.exercisesContainer);

        ExerciseJsonHandler exerciseJsonHandler = new ExerciseJsonHandler();
        List<Exercise> exercises = exerciseJsonHandler.loadExercises(this);

        int i = 0;
        for (Exercise exercise : exercises) {
            i++;
            MaterialCardView cardView = new MaterialCardView(this);
            cardView.setCardBackgroundColor(getResources().getColor(R.color.card_background_color));
            cardView.setRadius(16);
            cardView.setCardElevation(8);
            cardView.setContentPadding(16, 16, 16, 16);
            cardView.setUseCompatPadding(true);

            LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    450
            );
            cardLayoutParams.setMargins(16, 16, 16, 16);
            cardView.setLayoutParams(cardLayoutParams);

            LinearLayout cardContent = new LinearLayout(this);
            cardContent.setOrientation(LinearLayout.HORIZONTAL);
            cardContent.setGravity(Gravity.CENTER_VERTICAL);


            TextView exerciseTextView = new TextView(this);
            exerciseTextView.setText(exercise.getName());
            exerciseTextView.setTextColor(getResources().getColor(android.R.color.black));
            exerciseTextView.setTextSize(22); // Increase text size
            exerciseTextView.setPadding(16, 0, 0, 0); // Add padding to the left of the text


            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            );
            exerciseTextView.setLayoutParams(textLayoutParams);

            ImageView exerciseImageView = new ImageView(this);

            int imageResId = getResources().getIdentifier(exercise.getImagePath(), "drawable", getPackageName());
            if (imageResId != 0) {
                exerciseImageView.setImageResource(imageResId);
            } else {
                exerciseImageView.setImageResource(R.drawable.other_removebg_preview); // Placeholder image
            }
            exerciseImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                    300,
                    300
            );
            imageLayoutParams.setMargins(16, 0, 16, 0);
            exerciseImageView.setLayoutParams(imageLayoutParams);


            cardContent.addView(exerciseTextView);
            cardContent.addView(exerciseImageView);

            cardView.addView(cardContent);


            int finalI = i;
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                intent.putExtra("EXERCISE_ID", String.valueOf(finalI));
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Clicked on " + exercise.getName(), Toast.LENGTH_SHORT).show();
            });


            exercisesContainer.addView(cardView);
        }
    }
}
