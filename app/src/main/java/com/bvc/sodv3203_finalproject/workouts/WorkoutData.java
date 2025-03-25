package com.bvc.sodv3203_finalproject.workouts;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorkoutData {

    private static final WorkoutData _instance = new WorkoutData();

    protected List<WorkoutRoutine> routines = new ArrayList<>();

    private WorkoutData(){

    }

    public static WorkoutData getInstance(){

        return _instance;
    }

    public int add(WorkoutRoutine routine){

        //This method helps shorten syntax massively.
        this.routines.add(routine);

        return 0;
    }

    public int addAll(@NonNull List<WorkoutRoutine> routines){
        this.routines.addAll(routines);

        return 0;
    }

    public WorkoutRoutine get(int index){
        return routines.get(index);
    }

    public WorkoutRoutine get(WorkoutRoutine routine){
        int index = routines.indexOf(routine);

        return routines.get(index);
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();

        try{
            JSONArray routinesArr = new JSONArray();

            for(int i = 0; i < this.routines.size(); i++){
                routinesArr.put(
                        routines.get(i).toJSON()
                );
            }

            obj.put(WorkoutJSONKeys.DATA_ROUTINES, routinesArr);

            return obj;

        }catch (JSONException e){
            throw new RuntimeException("ERR: JSONException during singleton JSON serialization.");
        }

    }

    /** Takes in JSON data and adds the routines to the workoutData.
     *
     *  It doesn't clear the array so that it remains dynamic in case we make an API
     *  that provides data persistency.
     *
     *  TL;DR if you're gonna synchronize with this, clear the array first.
     *
     * @param obj The JSONObject derived from a file.
     */
    @SuppressLint("NewApi") //Because its stupid.
    public void addFromJSON(JSONObject obj){

        try {
            JSONArray arr = obj.getJSONArray(WorkoutJSONKeys.DATA_ROUTINES);

            for(int i = 0; i < arr.length(); i++){
                routines.add(
                  WorkoutRoutine.fromJSON(arr.getJSONObject(i))
                );
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public void addFromJSON(String jsonStr){
        try {
            addFromJSON(new JSONObject(jsonStr));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
