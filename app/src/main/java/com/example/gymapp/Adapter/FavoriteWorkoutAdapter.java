package com.example.gymapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gymapp.Domain.Favourite;
import com.example.gymapp.R;

import java.util.List;

public class FavoriteWorkoutAdapter extends RecyclerView.Adapter<FavoriteWorkoutAdapter.FavoriteViewHolder> {

    private List<Favourite> favouriteList;
    private Context context;
    private OnFavoriteClickListener listener;

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Favourite favourite);
    }

    public FavoriteWorkoutAdapter(List<Favourite> favouriteList, OnFavoriteClickListener listener, Context context) {
        this.favouriteList = favouriteList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_workout_rectangle, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_workout_rectangle, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favourite favourite = favouriteList.get(position);

        // Load image using Glide
        int resId = context.getResources().getIdentifier(
                favourite.getPicPath(),
                "drawable",
                context.getPackageName()
        );
        Glide.with(context)
                .load(resId)
                .into(holder.workoutImage);

        holder.workoutName.setText(favourite.getWorkoutName());
        holder.workoutExercises.setText(favourite.getLessons().size() + " Exercises");
        holder.workoutCalories.setText(favourite.getKcal() + " Kcal");
        holder.workoutDuration.setText(favourite.getDuration());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFavoriteClick(favourite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView workoutImage;
        TextView workoutName, workoutExercises, workoutCalories, workoutDuration;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutImage = itemView.findViewById(R.id.pic5);
            workoutName = itemView.findViewById(R.id.Rtitle);
            workoutExercises = itemView.findViewById(R.id.Rexercises);
            workoutCalories = itemView.findViewById(R.id.Rcalories);
            workoutDuration = itemView.findViewById(R.id.Rduration);
        }
    }
}