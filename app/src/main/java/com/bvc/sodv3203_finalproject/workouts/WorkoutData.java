package com.bvc.sodv3203_finalproject.workouts;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.bvc.sodv3203_finalproject.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorkoutData {

    public static final String FILE_NAME = "workout_data.json";

    private static final WorkoutData _instance = new WorkoutData();

    protected List<WorkoutRoutine> routines = new ArrayList<>();

    private WorkoutData(){}

    public static WorkoutData getInstance(){    return _instance;   }

    // === ABSTRACTION METHODS ===

    public boolean add(@NonNull WorkoutRoutine routine){

        //This method helps shorten syntax massively.
        return this.routines.add(routine);
    }

    public boolean addAll(@NonNull List<WorkoutRoutine> routines){

        return this.routines.addAll(routines);
    }

    public WorkoutRoutine get(int index){
        return routines.get(index);
    }

    public WorkoutRoutine get(WorkoutRoutine routine){

        //Linear search algorithm
        //Binary search algorithm was too buggy to implement.
        for(int i = 0; i < routines.size(); i++){
            if(routine.name.equals(routines.get(i).name)) return routines.get(i);
        }

        return null;
    }

    public int indexOf(String routineName){

        for(int i = 0; i < routines.size(); i++){

            if(routines.get(i).name.equals(routineName)){
                return i;
            }
        }

        return -1;
    }

    public int length(){
        return routines.size();
    }

    public List<WorkoutRoutine> routines(){
        return routines;
    }

    // === END OF ABSTRACTION METHODS ===

    public JSONObject toJSON(){
        try{
            JSONObject obj = new JSONObject();
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

    /**
     * Routines are meant to have unique names.
     * Therefore, we can search by them.
     *
     * @param name Name of the routine
     * @return `true` if routine exists
     */
    public boolean hasName(@NonNull String name){

        if(this.routines.isEmpty()) return false;

        for(int i = 0; i < routines.size(); i++){
            if(routines.get(i).name.equals(name)){
                return true;
            }
        }

        return false;
    }

    public void saveData(Context context){
        Utility.writeToFile(context, FILE_NAME, this.toJSON().toString());
    }

    public void syncFromFile(Context context){

        //Still broken, but it's supposed to only do it if the file exists.

        String data = Utility.readFromFile(context, FILE_NAME);

        routines.clear();

        addFromJSON(data);
    }


    @SuppressLint("NewApi")
    public void startUp(Context context) {

        /*
          So, here's the problem:

          Because android studio/the android framework is being
          incredibly dense, we have to THROW a custom exception all the way from
          the Utility.readFromFile() function
          THEN, we're going to catch it here.

          Catching the error will tell us that the file doesn't exist
          Therefore, we write an empty file and THEN we can proceed.

          This should not work. But, it does. It's stupid that this is
          what I have to do to get it to work.
          But, it works.
         */

        try {
            syncFromFile(context);
        } catch(DataFileNotFoundException e){
            saveData(context);
        }

    }
}
