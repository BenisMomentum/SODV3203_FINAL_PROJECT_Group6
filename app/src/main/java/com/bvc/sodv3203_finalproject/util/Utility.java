package com.bvc.sodv3203_finalproject.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.bvc.sodv3203_finalproject.workouts.DataFileNotFoundException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;

public class Utility {

    public static final String EDIT_WORKOUT_BUNDLE_KEY = "routine_to_edit";
    public static final String WORKOUT_MODE_BUNDLE_KEY = "routine";
    public static final String DEBUG_CODE = "_dbg";

    /**
     *   @param  input   The source input for the text
     *   @return         The text from the input
     */
    //Yippeeee, special Javadoc formatting
    public static String getText(EditText input){
        return input.getText().toString().trim();
    }

    /**
     *   Eases syntax for displaying Toast messages
     *
     * @param   text    The message to be displayed in a Toast
     * @param   len_short Whether to display the message with LENGTH_SHORT (true) or LENGTH_LONG (false)
     */

    public static void displayMsg(Context context, String text, boolean len_short){

        final int length = (len_short) ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;

        Toast.makeText(context, text, length).show();
    }


    @SuppressLint("NewApi")
    public static DayOfWeek getDay(String name){

        name = name.substring(0, 3).toLowerCase(); //Turns Monday into mon

        switch(name){

            case "sun": return DayOfWeek.SUNDAY;
            case "mon": return DayOfWeek.MONDAY;
            case "tue": return DayOfWeek.TUESDAY;
            case "wed": return DayOfWeek.WEDNESDAY;
            case "thu": return DayOfWeek.THURSDAY;
            case "fri": return DayOfWeek.FRIDAY;
            case "sat": return DayOfWeek.SATURDAY;

            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }

    }

    public static void writeToFile(Context context, String filename, String data){

        if(filename == null || data == null) return;
        if(filename.isBlank()) return;

        //Using try with resources to auto-close
        try{

            FileOutputStream fstream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            fstream.write(data.getBytes());

            fstream.close();

        } catch (FileNotFoundException e) {
            //By far one of the stupidest hacks I've had to perform.
            throw new DataFileNotFoundException("ERR: FILE NOT FOUND\n\n" + e.getMessage());

        } catch (IOException e) {
            throw new RuntimeException("ERR: IO EXCEPTION WHEN SAVING TO FILE\n\n" + e.getMessage());
        }
    }

    public static String readFromFile(Context context, String filename){

        if(filename == null) return null;
        if(filename.isBlank()) return null;

        StringBuilder jsonString = new StringBuilder();
        String line;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput(filename)))){

            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }

            return jsonString.toString();

        } catch (FileNotFoundException e) {
            throw new DataFileNotFoundException("ERR: FILE NOT FOUND\n\n" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("ERR: IO EXCEPTION WHEN SAVING TO FILE\n\n" + e.getMessage());
        }

    }

}
