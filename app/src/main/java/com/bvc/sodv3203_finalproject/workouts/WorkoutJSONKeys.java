package com.bvc.sodv3203_finalproject.workouts;


//Centralized location for JSON keys.
//Doesn't require memorizing keys
public class WorkoutJSONKeys {

    //When parsing from file
    public static String NAME = "name";
    public static String SETS = "sets";
    public static String REPS = "reps";
    public static String TARGET_MUSCLE = "target_muscle";

    //When parsing from API

    public static String API_NAME = "name";
    public static String API_MUSCLE = "target";

    //WorkoutRoutine's tags

    public static String ROUTINE_WORKOUTS = "workouts";
    public static String ROUTINE_DAYS = "workoutDays";

    //WorkoutData's tags
    public static String DATA_ROUTINES = "routines";
}


