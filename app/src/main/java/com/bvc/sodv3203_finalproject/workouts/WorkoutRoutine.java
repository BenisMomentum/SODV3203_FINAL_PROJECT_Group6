package com.bvc.sodv3203_finalproject.workouts;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class WorkoutRoutine {

    public List<Workout> workouts;

    public String name;

    //Could be possible that people have 2 leg days in a week, gotta account for it.
    public DayOfWeek[] workoutDays;

    public WorkoutRoutine(String name, DayOfWeek[] workoutDays) {
        this.name = name;
        this.workoutDays = workoutDays;

        //For fast indexing during render
        workouts = new ArrayList<>();
    }

    public WorkoutRoutine(List<Workout> workouts, String name, DayOfWeek[] workoutDays) {
        this.name = name;
        this.workoutDays = workoutDays;

        this.workouts = new ArrayList<>();

        if(!workouts.isEmpty()){
            this.workouts.addAll(workouts);
        }
    }

    //The following functions are to
    //Simplify and shorten future syntax.
    public int add(Workout workout){
        this.workouts.add(workout);

        return 0;
    }

    public Workout get(int index){
        return this.workouts.get(index);
    }
}
