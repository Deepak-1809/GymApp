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
import com.example.gymapp.databinding.ViewholderWorkoutBinding;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.Viewholder> {
    private ArrayList<Workout> list;
    private Context context;

    public WorkoutAdapter(ArrayList<Workout> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderWorkoutBinding binding = ViewholderWorkoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.binding.titletext.setText(list.get(position).getTitle());

        String imageName = list.get(position).getPicPath();
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(resId)
                .into(holder.binding.titlepic);

        holder.binding.exercisetext.setText(list.get(position).getLessons().size() + " Exercises");
        holder.binding.caloriestext.setText(list.get(position).getKcal() + " Kcal");
        holder.binding.durationtext.setText(list.get(position).getDurationAll());

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, WorkoutActivity.class);
            intent.putExtra("object", list.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void updateList(ArrayList<Workout> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        ViewholderWorkoutBinding binding;

        public Viewholder(ViewholderWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
