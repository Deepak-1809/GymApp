    package com.example.gymapp.Domain;

    import java.io.Serializable;
    import java.util.ArrayList;

    public class Favourite implements Serializable {
        private String workoutName;
        private int workoutImage;
        private String picPath; // For loading from resources
        private String description;
        private int kcal;
        private String duration;
        private ArrayList<Lessons> lessons;

        public Favourite(String workoutName, int workoutImage, String picPath,
                         String description, int kcal, String duration,
                         ArrayList<Lessons> lessons) {
            this.workoutName = workoutName;
            this.workoutImage = workoutImage;
            this.picPath = picPath;
            this.description = description;
            this.kcal = kcal;
            this.duration = duration;
            this.lessons = lessons;
        }

        // Getters
        public String getWorkoutName() { return workoutName; }
        public int getWorkoutImage() { return workoutImage; }
        public String getPicPath() { return picPath; }
        public String getDescription() { return description; }
        public int getKcal() { return kcal; }
        public String getDuration() { return duration; }
        public ArrayList<Lessons> getLessons() { return lessons; }
    }