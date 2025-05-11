package com.example.gymapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gymapp.Adapter.WorkoutAdapter;
import com.example.gymapp.Domain.Favourite;
import com.example.gymapp.Domain.Lessons;
import com.example.gymapp.Domain.Workout;
import com.example.gymapp.R;
import com.example.gymapp.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public List<Favourite> favoriteWorkoutList = new ArrayList<>();



    private ArrayList<Workout> getMainWorkouts() {
        ArrayList<Workout> list = new ArrayList<>();
        list.add(new Workout("pic1_6", "Running", "Start your day with a quick run.", 250, "12:00", getLessons1()));
        list.add(new Workout("pic_2", "Stretching", "Loosen up with a morning stretch.", 180, "33:00", getLessons2()));
        list.add(new Workout("pic_3", "Yoga", "Relax and improve flexibility.", 210, "38:00", getLessons3()));
        return list;
    }

    // For See All screen (8 workouts)
    private ArrayList<Workout> getAllWorkouts() {
        ArrayList<Workout> list = getMainWorkouts(); // Start with the 3 main workouts
        // Add 5 additional workouts
        list.add(new Workout("pic_4", "Chest", "Build strong chest muscles", 320, "28:00", getLessons4()));
        list.add(new Workout("pic_5", "Back", "Strengthen your back", 280, "25:00", getLessons5()));
        list.add(new Workout("pic_6", "Arms", "Toned arms workout", 240, "22:00", getLessons6()));
        list.add(new Workout("pic7", "Abs", "Core strengthening exercises", 200, "20:00", getLessons7()));
        list.add(new Workout("pic8", "Legs", "Powerful lower body workout", 350, "35:00", getLessons8()));
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        binding.imageView3.setOnClickListener(v -> {
            String videoUrl = "https://youtu.be/9V9csctSKj0?si=zqE56NvRaCVPGCn_";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(android.net.Uri.parse(videoUrl));
            startActivity(intent);
        });
        binding.HomeIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Already On Home Screen", Toast.LENGTH_SHORT).show();
        });



        // Setup main screen workouts (3 only)
        binding.view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(new WorkoutAdapter(getMainWorkouts(), this));

        // See All click handler
        binding.SeeAll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SeeAllActivity.class);
            intent.putExtra("allWorkouts", getAllWorkouts());
            startActivity(intent);
        });

        ImageView favIconMain = findViewById(R.id.FavIcon);
        favIconMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
            if (favoriteWorkoutList != null) {
                intent.putExtra("favoriteWorkoutList", (Serializable) favoriteWorkoutList);
            } else {
                Log.e("MainActivity", "favoriteWorkoutList is null!");
            }
            startActivity(intent);
        });
    }

    // Lesson methods remain unchanged below
    private ArrayList<Lessons> getLessons1() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "03:46", "HBPMvFkpNgE", "pic_1_1"));
        list.add(new Lessons("Lesson 2", "05:09", "N9C88z3g0Es", "pic_1_2"));
        list.add(new Lessons("Lesson 3", "03:56", "cQCsCw8jCYo", "pic_1_3"));
        return list;
    }

    private ArrayList<Lessons> getLessons2() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "20:23", "L3eImBAXT7I", "pic_2_1"));
        list.add(new Lessons("Lesson 2", "08:56", "FI51zRzgIe4", "pic_2_2"));
        list.add(new Lessons("Lesson 3", "09:11", "t2jel6q1GRk", "pic_2_3"));
        list.add(new Lessons("Lesson 4", "15:17", "75pA0MaLvy0", "pic_2_4"));
        return list;
    }

    private ArrayList<Lessons> getLessons3() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "11:38", "C2RAjUEAoLI", "pic_3_1"));
        list.add(new Lessons("Lesson 2", "27:00", "Eml2xnoLpYE", "pic_3_2"));
        list.add(new Lessons("Lesson 3", "25:00", "v7SN-d4qXx0", "pic_3_3"));
        list.add(new Lessons("Lesson 4", "08:40", "W2hRlGrgoUQ", "pic_3_4"));
        return list;
    }

    private ArrayList<Lessons> getLessons4() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "08:00", "-iWjdKWNpNg", "pic_4_1"));
        list.add(new Lessons("Lesson 2", "10:00", "IjiBNxco2oI", "pic_4_2"));
        list.add(new Lessons("Lesson 3", "10:00", "dnwJtCtQlZ4", "pic_4_3"));
        return list;
    }

    private ArrayList<Lessons> getLessons5() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "08:00", "0xvdPDQTrrc", "pic_5_1"));
        list.add(new Lessons("Lesson 2", "09:00", "X6vpkfoTd64", "pic_5_2"));
        list.add(new Lessons("Lesson 3", "08:00", "12xHxUnBEiI", "pic_5_3"));
        return list;
    }

    private ArrayList<Lessons> getLessons6() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "07:00", "JyV7mUFSpXs", "pic_6_1"));
        list.add(new Lessons("Lesson 2", "08:00", "d24nAtn8w4E", "pic_6_2"));
        list.add(new Lessons("Lesson 3", "07:00", "MfMxT_jXcPE", "pic_6_3"));
        return list;
    }

    private ArrayList<Lessons> getLessons7() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "06:00", "Tn-XvYG9x7w", "pic7_1"));
        list.add(new Lessons("Lesson 2", "08:00", "9p7-YC91Q74", "pic7_2"));
        list.add(new Lessons("Lesson 3", "06:00", "ITPcN8U_tYg", "pic7_3"));
        return list;
    }

    private ArrayList<Lessons> getLessons8() {
        ArrayList<Lessons> list = new ArrayList<>();
        list.add(new Lessons("Lesson 1", "10:00", "G5JVg3WEZmM", "pic8_1"));
        list.add(new Lessons("Lesson 2", "12:00", "H6mRkx1x77k", "pic8_2"));
        list.add(new Lessons("Lesson 3", "08:00", "6W5jQgYpq-o", "pic8_3"));
        return list;
    }
}