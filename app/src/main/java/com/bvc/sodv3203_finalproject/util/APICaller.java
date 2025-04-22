package com.bvc.sodv3203_finalproject.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bvc.sodv3203_finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * This class calls our API. Some methods are abstracted for the sake of ease.
 */
public class APICaller extends AsyncTask<String, Void, String> {

    public static final String API_RET_KEY = "exercises";
    private static final String API_URL = "https://api.api-ninjas.com/v1/exercises";
    private static final String API_KEY = "BrcmuaWFWMgEDYFRX2rACA==GTJG6pXv69ECiGwA";

    /**
     * Gets the exercise by calling our API using its name as apart of its query.
     * @param name The exercise's name, derived from the user's input.
     * @return a Workout array in the form of a JSON object. Null if error.
     */
    public static JSONObject getExercise(String name, Context context){
        try {

            //Essentially, we call the API and pack the resulting exercised up into JSON
            //We pack it into a string, then a JSON, and return the object with the correct return key

            AsyncTask<String, Void, String> dataSrc = new APICaller().execute(API_URL + "?name=" + name.trim());

            String data = dataSrc.get();

            JSONObject obj = new JSONObject();

            obj.put(API_RET_KEY, new JSONArray(data));

            return obj;

        } catch (JSONException | ExecutionException | InterruptedException e) {
            Utility.displayMsg(context, Utility.getErrorMessage(context, R.string.ErrMsg_APICallError), false);

            return null;
        }
    }

    /**
     * Performs the api call in the background and returns a JSON string.
     * @param urls The parameters of the task.
     *
     * @return JSON string of workout list.
     */
    @Override
    protected String doInBackground (String... urls) {

        //Batched together for bytecode efficiency
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String jsonData = null;

        try {

            URL baseURL = new URL(urls[0]);

            connection = (HttpURLConnection) baseURL.openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty("X-Api-Key", API_KEY);

            connection.connect();

            InputStream istream = connection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(istream));

            String line;

            while((line = reader.readLine()) != null){
                buffer.append(line).append('\n');
            }

            if(buffer.length() == 0) return null;

            jsonData = buffer.toString();


            reader.close();
            istream.close();


        } catch(IOException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null) connection.disconnect();
        }

        return jsonData;
    }
}
