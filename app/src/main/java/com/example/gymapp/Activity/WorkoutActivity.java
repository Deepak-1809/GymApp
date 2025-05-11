package com.example.gymapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.gymapp.Domain.Favourite;
import com.example.gymapp.Domain.Workout;
import com.example.gymapp.databinding.ActivityWorkoutBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {
    ActivityWorkoutBinding binding;
    private Workout workout;
    private static List<Favourite> favoriteWorkoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        getObject();
        setVariable();
        setupFavoriteIcon();
    }

    private void getObject() {
        workout = (Workout) getIntent().getSerializableExtra("object");
    }

    private void setVariable() {
        int resId = getResources().getIdentifier(workout.getPicPath(), "drawable", getPackageName());
        Glide.with(this)
                .load(resId)
                .into(binding.pic);

        binding.backBtn.setOnClickListener(v -> finish());

        binding.titleTxt.setText(workout.getTitle());
        binding.exercisetext.setText(workout.getLessons().size()+" Exercise");
        binding.caloriestext.setText(workout.getKcal() + " Kcal");
        binding.durationtext.setText(workout.getDurationAll());
        binding.descriptionTxt.setText(workout.getDescription());

        binding.view3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.view3.setAdapter(new com.example.gymapp.Adapter.LessonsAdapter(workout.getLessons()));
    }

    // In your setupFavoriteIcon() method:
    private void setupFavoriteIcon() {
        binding.imageView7.setOnClickListener(v -> {
            // Check if already added
            for (Favourite fav : favoriteWorkoutList) {
                if (fav.getWorkoutName().equals(workout.getTitle())) {
                    Toast.makeText(this, "Already added to Favorites", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Create new favorite with all needed data
            Favourite favoriteWorkout = new Favourite(
                    workout.getTitle(),
                    getResources().getIdentifier(workout.getPicPath(), "drawable", getPackageName()),
                    workout.getPicPath(),
                    workout.getDescription(),
                    workout.getKcal(),
                    workout.getDurationAll(),
                    workout.getLessons()
            );

            favoriteWorkoutList.add(favoriteWorkout);
            Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
        });
    }

    public static List<Favourite> getFavoriteWorkoutList() {
        return favoriteWorkoutList;
    }
}
