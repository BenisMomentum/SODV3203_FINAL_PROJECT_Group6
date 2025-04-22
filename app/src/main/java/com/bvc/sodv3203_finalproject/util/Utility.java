package com.bvc.sodv3203_finalproject.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.HomePageActivity;
import com.bvc.sodv3203_finalproject.SearchWorkoutActivity;
import com.bvc.sodv3203_finalproject.SettingsActivity;
import com.bvc.sodv3203_finalproject.WorkoutLogActivity;
import com.bvc.sodv3203_finalproject.workouts.DataFileNotFoundException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;

/**
 * The Utility class is filled with common methods and keys that we will need
 * throughout our entire application. It is to centralize any data necessary
 */
public class Utility {

    public static final String EDIT_WORKOUT_BUNDLE_KEY = "routine_to_edit";
    public static final String WORKOUT_MODE_BUNDLE_KEY = "routine";
    public static final String DEBUG_CODE = "_dbg";
    

    /**
     * Used to keep code DRY and set our home bar button's selected item listeners.
     * It navigates to a new page if a new page is selected.
     *
     * First 4 parameters are each button so that we may modify them directly.
     *
     * @param home - The direct reference to the home button
     * @param workouts - The direct reference to the workouts button
     * @param search - The direct reference to the search button
     * @param settings - The direct reference to the settings button
     * @param parent - The parent activity
     * @return true if success, false if same page is selected.
     */
    public static void SetupHomeBarButtons(ImageButton home,
                                           ImageButton workouts,
                                           ImageButton search,
                                           ImageButton settings,
                                           AppCompatActivity parent){

        //Helps shorten our syntax:
        INavigation navigator = activityClass -> {
            final Intent intent = new Intent(parent.getApplicationContext(), activityClass);
            parent.startActivity(intent);
        };

        home.setOnClickListener(v -> navigator.navigateTo(HomePageActivity.class));
        workouts.setOnClickListener(v -> navigator.navigateTo(WorkoutLogActivity.class));
        search.setOnClickListener(v -> navigator.navigateTo(SearchWorkoutActivity.class));
        settings.setOnClickListener(v -> navigator.navigateTo(SettingsActivity.class));

        //Now we disable whichever one that coressponds to the current page.
        //Please note: since the Home Page doesn't have a home page button, it cannot be disabled.
        if (parent instanceof WorkoutLogActivity){
            workouts.setEnabled(false);
        }
        else if(parent instanceof SearchWorkoutActivity){
            search.setEnabled(false);
        }
        else if(parent instanceof SettingsActivity){
            search.setEnabled(false);
        }
    }

    /**
     *   Acquires the data from an EditText.
     *   Used to streamline syntax
     *
     *   @param  input   The source input for the text
     *   @return         The text from the input
     */
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


    /** Basically, a much better valueOf() for the DayOfWeek enum we're using
     *
     * @param name The name of the day we wish to convert
     * @return The resulting enum value.
     */
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

    /** Using the parent context, we write data to the inputted file.
     *
     * @param context The parent context we need in order to open the file ouput
     * @param filename The filename we're using (to keep this method decoupled)
     * @param data The data we wish to write
     */
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

    /** A simple method to read from the specified file using the parent context.
     *
     * @param context The parent context
     * @param filename The file name we wish to read from
     * @return A string of the file's contents.
     */
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

    /**
     *  The function is used to get the system's theme that's being used.
     * @param context The context needed in order to use this.
     * @return Returns true if system mode is dark. Default return is true.
     */
    public static boolean GetSystemIsDark(Context context){
        switch(context.getResources().getConfiguration().uiMode){
            case Configuration.UI_MODE_NIGHT_YES:
                return true;
            case Configuration.UI_MODE_NIGHT_NO:
                return false;
            default:
                return true; //Default is dark mode.
        }
    }

    public static String getErrorMessage(Context context, int resid){
        return String.valueOf(context.getText(resid));
    }

}
