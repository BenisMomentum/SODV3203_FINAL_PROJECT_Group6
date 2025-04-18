package com.bvc.sodv3203_finalproject.workouts;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Workout {

    //The basis for our data.

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
    //Anyone who tells me otherwise should subject themselves to it.
    public Workout(JSONObject obj) throws JSONException{

        this.name = obj.getString(WorkoutJSONKeys.NAME);

        this.targetMuscle = TargetMuscle.valueOf(obj.getString(WorkoutJSONKeys.TARGET_MUSCLE));

        this.sets = obj.getInt(WorkoutJSONKeys.SETS);
        this.reps = obj.getInt(WorkoutJSONKeys.REPS);

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

    public static Workout fromJSON(JSONObject object){

        try{

            String name = object.getString(WorkoutJSONKeys.NAME);
            TargetMuscle muscle = TargetMuscle.valueOf(object.getString(WorkoutJSONKeys.TARGET_MUSCLE));
            int sets = object.getInt(WorkoutJSONKeys.SETS);
            int reps = object.getInt(WorkoutJSONKeys.REPS);

            return new Workout(name, sets, reps, muscle);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public static Workout ParseFromAPI(JSONObject obj){

        String name = null;
        TargetMuscle muscle = null;

        try {
            name = obj.getString(WorkoutJSONKeys.API_NAME);

            String targetStr = obj.getString(WorkoutJSONKeys.API_MUSCLE);

            muscle = TargetMuscle.valueOf(targetStr.toUpperCase());

            return new Workout(name, muscle);
        } catch (JSONException e) {
            throw new RuntimeException("==ERROR WHEN PARSING FROM API==\n\n" + e.getMessage());
        }
    }


}
