package com.bvc.sodv3203_finalproject.workouts;

import android.util.Log;

import java.lang.annotation.Target;

public class Workout {

    public String name;
    public String description;
    public TargetMuscle targetMuscle;
    public int sets = 0;
    public int reps = 0;


    public Workout(String name, String description, int sets, int reps, TargetMuscle target) {
        this.name = name;
        this.description = description;
        this.sets = sets;
        this.reps = reps;

        this.targetMuscle = target;
    }

    public Workout(String name, String description, TargetMuscle target) {
        this.name = name;
        this.description = description;
        this.targetMuscle = target;
    }

    public Workout(String name, String targetMuscle, String description, int sets, int reps) {
        this.name = name;
        this.description = description;
        this.sets = sets;
        this.reps = reps;

        this.targetMuscle = TargetMuscle.valueOf(targetMuscle);
    }

    public Workout(String name, String description, String targetMuscle) {
        this.name = name;
        this.description = description;
        this.targetMuscle = TargetMuscle.valueOf(targetMuscle);
    }

    public void parseIntoSets(String input){
        try {
            this.sets = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Log.d("Workout_Parse", "Err: Could not parse string: \n\t" + input);
        }
    }

    public void parseIntoReps(String input){
        try {
            this.reps = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Log.d("Workout_Parse", "Err: Could not parse string: \n\t" + input);
        }
    }
}
