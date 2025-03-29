package com.bvc.sodv3203_finalproject.workouts;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.R;

public class WorkoutFactory {

    private static final int titlePadding = 12;
    private static final float titleTextSize = 20;
    private static final int DATE_DISPLAY_MARGIN = 5;

    //We do this to shorten syntax and centralize variables.
    private static final int textSizeUnit = Dimension.SP;
    private static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;



    public static LinearLayout createWidget(WorkoutRoutine routine, Context context){

        //LAYOUT CREATION AND SETTING
        LinearLayout layout = new LinearLayout(context);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams( new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));

        Context layoutContext = layout.getContext();

        //TITLE CREATION AND SETTING
        TextView title = new TextView(context);

        title.setText(routine.name);
        title.setPadding(titlePadding,titlePadding,titlePadding,titlePadding);
        title.setTextSize(textSizeUnit, titleTextSize);
        title.setWidth(WRAP_CONTENT);
        title.setHeight(WRAP_CONTENT);
        title.setTypeface(null, Typeface.BOLD);

        layout.addView(title);

        LinearLayout dateDisplayLayout = new LinearLayout(context);

        LinearLayout.LayoutParams dateDisplayLayoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);

        dateDisplayLayoutParams.setMargins(DATE_DISPLAY_MARGIN, DATE_DISPLAY_MARGIN, DATE_DISPLAY_MARGIN, DATE_DISPLAY_MARGIN);

        dateDisplayLayout.setOrientation(LinearLayout.HORIZONTAL);
        dateDisplayLayout.setLayoutParams(dateDisplayLayoutParams);


        Context dateDisplayContext = dateDisplayLayout.getContext();

        return layout;
    }

}
