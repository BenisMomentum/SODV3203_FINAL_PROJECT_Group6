package com.bvc.sodv3203_finalproject.workouts;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WorkoutData {

    private static final WorkoutData _instance = new WorkoutData();

    public List<WorkoutRoutine> routines = new ArrayList<>();

    private WorkoutData(){

    }

    public static WorkoutData getInstance(){

        return _instance;
    }

    public int add(WorkoutRoutine routine){

        //This method helps shorten syntax massively.
        routines.add(routine);

        return 0;
    }

    public int addAll(@NonNull List<WorkoutRoutine> routines){
        routines.addAll(routines);

        return 0;
    }

    public WorkoutRoutine get(int index){
        return routines.get(index);
    }

    public WorkoutRoutine get(WorkoutRoutine routine){
        int index = routines.indexOf(routine);

        return routines.get(index);
    }

}
