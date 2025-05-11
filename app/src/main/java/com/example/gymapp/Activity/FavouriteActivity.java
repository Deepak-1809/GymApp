package com.example.gymapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymapp.Adapter.FavoriteWorkoutAdapter;
import com.example.gymapp.Domain.Favourite;
import com.example.gymapp.Domain.Workout;
import com.example.gymapp.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements FavoriteWorkoutAdapter.OnFavoriteClickListener {

    private RecyclerView favoriteRecyclerView;
    private List<Favourite> favoriteWorkoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        favoriteRecyclerView = findViewById(R.id.recyclerViewFavourite);
        findViewById(R.id.backBtn3).setOnClickListener(v -> finish());

        // SAFELY get list from static method
        List<Favourite> tempList = WorkoutActivity.getFavoriteWorkoutList();
        if (tempList != null) {
            favoriteWorkoutList = new ArrayList<>(tempList); // avoid modifying original list
        }

        // Setup adapter only if we have data
        if (!favoriteWorkoutList.isEmpty()) {
            FavoriteWorkoutAdapter adapter = new FavoriteWorkoutAdapter(
                    favoriteWorkoutList,
                    this,
                    this
            );
            favoriteRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            favoriteRecyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No Favorites Yet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFavoriteClick(Favourite favourite) {
        // Convert Favourite back to Workout to pass to WorkoutActivity
        Workout workout = new Workout(
                favourite.getPicPath(),
                favourite.getWorkoutName(),
                favourite.getDescription(),
                favourite.getKcal(),
                favourite.getDuration(),
                favourite.getLessons()
        );

        Intent intent = new Intent(this, WorkoutActivity.class);
        intent.putExtra("object", workout);
        startActivity(intent);
    }
}