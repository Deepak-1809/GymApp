package com.example.gymapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gymapp.Activity.WorkoutActivity;
import com.example.gymapp.Domain.Workout;
import com.example.gymapp.R;
import com.example.gymapp.databinding.ViewholderWorkoutRectangleBinding;

import java.util.ArrayList;

public class WorkoutRectangleAdapter extends RecyclerView.Adapter<WorkoutRectangleAdapter.ViewHolder> {
    private ArrayList<Workout> workouts;
    private Context context;

    public WorkoutRectangleAdapter(ArrayList<Workout> workouts, Context context) {
        this.workouts = workouts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderWorkoutRectangleBinding binding = ViewholderWorkoutRectangleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Workout workout = workouts.get(position);

        // Load image with fallback
        try {
            int resId = context.getResources().getIdentifier(
                    workout.getPicPath(), "drawable", context.getPackageName());
            Glide.with(context)
                    .load(resId)
                    .error(R.drawable.pic_1)
                    .into(holder.binding.pic5);
        } catch (Exception e) {
            holder.binding.pic5.setImageResource(R.drawable.pic_1);
        }

        holder.binding.Rtitle.setText(workout.getTitle());
        holder.binding.Rexercises.setText(workout.getLessons().size() + " Exercises");
        holder.binding.Rcalories.setText(workout.getKcal() + " Kcal");
        holder.binding.Rduration.setText(workout.getDurationAll());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WorkoutActivity.class);
            intent.putExtra("object", workout);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderWorkoutRectangleBinding binding;

        public ViewHolder(ViewholderWorkoutRectangleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
