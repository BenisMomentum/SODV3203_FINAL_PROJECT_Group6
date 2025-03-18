package com.bvc.sodv3203_finalproject.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Utility {

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
}
