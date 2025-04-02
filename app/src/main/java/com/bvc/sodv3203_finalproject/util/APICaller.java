package com.bvc.sodv3203_finalproject.util;

import android.os.AsyncTask;
import android.util.Log;

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

public class APICaller extends AsyncTask<String, Void, String> {

//    This class calls our API. Some methods are abstracted for the sake of ease.

    public static final String API_RET_KEY = "exercises";
    private static final String API_URL = "https://api.api-ninjas.com/v1/exercises";
    private static final String API_KEY = "BrcmuaWFWMgEDYFRX2rACA==GTJG6pXv69ECiGwA";

    public static JSONObject getExercise(String name){
        try {

            AsyncTask<String, Void, String> data = new APICaller().execute(API_URL + "?name=" + name.trim());

            JSONObject obj = new JSONObject();

            obj.put(API_RET_KEY, new JSONArray(data.get()));

            return obj;

        } catch (JSONException e) {
            Log.d("DEBUG_MSG", e.getMessage());

            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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
