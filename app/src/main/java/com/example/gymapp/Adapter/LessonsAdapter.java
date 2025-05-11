package com.example.gymapp.Adapter;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.gymapp.Domain.Lessons;
import com.example.gymapp.databinding.ViewholderExerciseBinding;
import java.util.ArrayList;
public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.Viewholder> {
    private final ArrayList<Lessons> list;
    private Context context;
    public LessonsAdapter(ArrayList<Lessons> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public LessonsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderExerciseBinding binding = ViewholderExerciseBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.titleText.setText(list.get(position).getTitle());
        holder.binding.durationText.setText(list.get(position).getDuration());

        int resId = context.getResources().getIdentifier(
                list.get(position).getPicPath(),
                "drawable",
                context.getPackageName()
        );
        Glide.with(context)
                .load(resId)
                .into(holder.binding.pic1);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + list.get(position).getLink()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + list.get(position).getLink()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size(); //
    }
    public static class Viewholder extends RecyclerView.ViewHolder {
        ViewholderExerciseBinding binding;

        public Viewholder(ViewholderExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
