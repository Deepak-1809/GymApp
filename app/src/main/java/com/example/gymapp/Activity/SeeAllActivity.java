package com.example.gymapp.Activity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.gymapp.Adapter.WorkoutAdapter;
import com.example.gymapp.Adapter.WorkoutRectangleAdapter;
import com.example.gymapp.Domain.Workout;
import com.example.gymapp.databinding.ActivitySeeallBinding;
import java.util.ArrayList;

public class SeeAllActivity extends AppCompatActivity {
    private ActivitySeeallBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeeallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Workout> workouts = (ArrayList<Workout>) getIntent().getSerializableExtra("allWorkouts");

        // Calculate item width for consistent sizing
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int itemWidth = (screenWidth - (3 * 16)) / 2; // 16dp margins on both sides

        // Setup RecyclerView
        binding.recyclerViewSeeAll.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.recyclerViewSeeAll.setLayoutManager(layoutManager);

        // Add item decoration for spacing
        binding.recyclerViewSeeAll.addItemDecoration(new GridSpacingItemDecoration(2, 16, true));

        // Use the rectangle adapter
        binding.recyclerViewSeeAll.setAdapter(new WorkoutRectangleAdapter(workouts, this));

        binding.backBtn3.setOnClickListener(v -> finish());
    }
}