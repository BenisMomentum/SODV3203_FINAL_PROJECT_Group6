package com.bvc.sodv3203_finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutFactory;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WorkoutLogActivity extends AppCompatActivity {


    public LinearLayout workoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_logs);

        workoutContainer = findViewById(R.id.workout_workoutContainer);

        workoutContainer.removeAllViewsInLayout();

        loadWorkoutData();

        new fetchDataTask().execute("https://www.api-ninjas.com/api/exercises");


    }


    @Override
    protected void onStart(){
        super.onStart();

    }


    private void loadWorkoutData() {

        List<WorkoutRoutine> routines = WorkoutData.getInstance().routines();

        Log.d("loadWorkoutData", "DATA LENGTH: " + WorkoutData.getInstance().length());

        for (int i = 0; i < routines.size(); i++) {

            workoutContainer.addView(WorkoutFactory.createWidget(routines.get(i), this));

        }

    }

    //this is sample try for connecting API. I adapted Pedro's code using outside resources
  private class fetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader =null;
            String data = null;

            try{

                String urlString = params[0];
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.setRequestProperty("X-Api-Key", "BrcmuaWFWMgEDYFRX2rACA==GTJG6pXv69ECiGwA");
                urlConnection.setRequestProperty("Content-Type", "application/json");


                urlConnection.connect();


                InputStream inputStream = urlConnection.getInputStream();

                StringBuilder buffer = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                data = buffer.toString();

                urlConnection.disconnect();//if(urlConnection != null before it is not necessary but it is possible to use it.
                reader.close();
                inputStream.close();

            }catch(IOException e){
                throw new RuntimeException(e);
            } finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try{
                        reader.close();
                    }catch(IOException e){
                        throw new RuntimeException(e);
                    }
                }
            }

            return data;
        }

        @Override
        public void onPostExecute(String jsonData){
            if (jsonData != null) {
                try{
                    JSONArray jsonArray = new JSONArray(jsonData);
                    List<Workout> workoutList = new ArrayList<>();
                    //String title = jsonObject.getString("title");



                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject exerciseObject = jsonArray.getJSONObject(i);
                        String name = exerciseObject.getString("name");
                        String muscle = exerciseObject.getString("muscle");
                        String type = exerciseObject.getString("type");
                        String difficulty = exerciseObject.getString("difficulty");
                    }


                }catch(JSONException e){
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
