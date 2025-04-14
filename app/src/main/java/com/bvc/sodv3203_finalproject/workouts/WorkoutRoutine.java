package com.bvc.sodv3203_finalproject.workouts;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public WorkoutRoutine(String name, List<Workout> workouts, DayOfWeek[] workoutDays) {
        this.name = name;
        this.workoutDays = workoutDays;

        this.workouts = new ArrayList<>();

        if(!workouts.isEmpty()){
            this.workouts.addAll(workouts);
        }
    }

    //The following functions are to
    //Simplify and shorten future syntax.
    public void add(Workout workout){
        boolean status = this.workouts.add(workout);

        if(!status) throw new RuntimeException("ERROR: UNABLE TO ADD WORKOUT TO ROUTINE\n\n");
    }

    public Workout get(int index){
        return this.workouts.get(index);
    }

    /**
     *  Because putting try-catches in constructors is weird,
     *  we have made a static function to take care of it for us.
     *
     * @param obj The JSONObject that must be read and parsed.
     * @return A new WorkoutRoutine with data from the derived JSON object.
     */
    @SuppressLint("NewApi")  //This API requirement is stupid and unnecessary
    public static WorkoutRoutine fromJSON(JSONObject obj){

        List<Workout> workouts = new ArrayList<>();

        try {

            String name = obj.getString(WorkoutJSONKeys.NAME);

            JSONArray workoutArr = obj.getJSONArray(WorkoutJSONKeys.ROUTINE_WORKOUTS);
            JSONArray daysArr = obj.getJSONArray(WorkoutJSONKeys.ROUTINE_DAYS);

            for(int i = 0; i < workoutArr.length(); i++){
                workouts.add(new Workout(workoutArr.getJSONObject(i)));
            }

            //Declared here because we don't know the length yet.
            //And we have to keep it all within the Try-Catch.
            DayOfWeek[] days = new DayOfWeek[daysArr.length()];

            for(int i = 0; i < days.length; i++){
                days[i] = DayOfWeek.valueOf(daysArr.get(i).toString());
            }

            return new WorkoutRoutine(name, workouts, days);

        } catch(JSONException e){
            throw new RuntimeException("ERR: Could not deserialize WorkoutRoutine from JSON\n" + e);
        }
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();



        JSONArray workoutArr = new JSONArray(),
                    daysArr = new JSONArray();


        for (DayOfWeek workoutDay : this.workoutDays) {
            daysArr.put(workoutDay.toString());
        }

        //Manual for-loop for efficiency
        for(int i = 0; i < workouts.size(); i++){
            workoutArr.put(workouts.get(i).toJSON());
        }

        try {

            obj.put(WorkoutJSONKeys.NAME, this.name);
            obj.put(WorkoutJSONKeys.ROUTINE_DAYS, daysArr);
            obj.put(WorkoutJSONKeys.ROUTINE_WORKOUTS, workoutArr);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    //--------------------------------------------
    // added to have it on WorkoutData for searching workout page
    public List<Workout> getWorkouts(){
        return workouts;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if(obj == null) return false;
        if(!(obj instanceof WorkoutRoutine)) return false;

        WorkoutRoutine wR = (WorkoutRoutine) obj;

        return this.name.equals(wR.name);
    }
}
