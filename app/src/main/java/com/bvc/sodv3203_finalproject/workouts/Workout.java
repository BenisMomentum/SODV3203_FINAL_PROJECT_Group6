package com.bvc.sodv3203_finalproject.workouts;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Workout {

    public String name;
    public TargetMuscle targetMuscle;
    public int sets = 0;
    public int reps = 0;


    public Workout(String name, int sets, int reps, TargetMuscle target) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;

        this.targetMuscle = target;
    }

    public Workout(String name, TargetMuscle target) {
        this.name = name;
        this.targetMuscle = target;
    }

    public Workout(String name, String targetMuscle, int sets, int reps) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;

        this.targetMuscle = TargetMuscle.valueOf(targetMuscle);
    }

    //-------------------------

    public String getName() {
        return name;
    }


    //--------------------

    public Workout(String name, String targetMuscle) {
        this.name = name;
        this.targetMuscle = TargetMuscle.valueOf(targetMuscle);
    }

    //I am averse to putting try-catches in constructors. This is easier.
    public Workout(JSONObject obj) throws JSONException{

        this.name = obj.getString(WorkoutJSONKeys.NAME);

        this.targetMuscle = TargetMuscle.valueOf(obj.getString(WorkoutJSONKeys.TARGET_MUSCLE));

        this.sets = obj.getInt(WorkoutJSONKeys.SETS);
        this.reps = obj.getInt(WorkoutJSONKeys.REPS);

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

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();

        try {
            obj.put(WorkoutJSONKeys.NAME, this.name);
            obj.put(WorkoutJSONKeys.TARGET_MUSCLE, this.targetMuscle.name().toUpperCase());
            obj.put(WorkoutJSONKeys.SETS, this.sets);
            obj.put(WorkoutJSONKeys.REPS, this.reps);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public static Workout ParseFromAPI(JSONObject obj){

        String name = null;
        TargetMuscle muscle = null;

        try {
            name = obj.getString(WorkoutJSONKeys.API_NAME);
            muscle = TargetMuscle.valueOf(obj.getString(WorkoutJSONKeys.API_MUSCLE));

            return new Workout(name, muscle);
        } catch (JSONException e) {
            throw new RuntimeException("==ERROR WHEN PARSING FROM API==\n\n" + e.getMessage());
        }
    }


}
