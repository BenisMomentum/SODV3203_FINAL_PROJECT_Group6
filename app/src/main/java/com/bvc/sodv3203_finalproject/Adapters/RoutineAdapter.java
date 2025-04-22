package com.bvc.sodv3203_finalproject.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ComplexColorCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.EditWorkoutRoutineActivity;
import com.bvc.sodv3203_finalproject.R;
import com.bvc.sodv3203_finalproject.WorkoutModeActivity;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

/**
 * The following adapter is used to display our workout routines in WorkoutLogActivity.
 * It also initializes a drop-down menu that allows us to enter workout mode for said routine,
 * to delete, and to edit the workout routine itself.
 */
public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>{

    protected Context parentContext;

    public RoutineAdapter(Context context){

        //The context is just here because some
        //functions need it.
        this.parentContext = context;

    }

    public class RoutineViewHolder extends RecyclerView.ViewHolder{

        protected TextView name;

        //The array is in the following order:
        //Sun, Mon, Tue, Wed, Thur, Fri, Sat
        protected TextView[] days;

        protected ImageButton dropdownbtn;

        protected LinearLayout container = null;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.RLRD_TV_name);

            dropdownbtn = itemView.findViewById(R.id.RLRD_ibtn_dropdown);

            this.days = new TextView[]{
                itemView.findViewById(R.id.RLRD_TV_Sun),
                itemView.findViewById(R.id.RLRD_TV_Mon),
                itemView.findViewById(R.id.RLRD_TV_Tue),
                itemView.findViewById(R.id.RLRD_TV_Wed),
                itemView.findViewById(R.id.RLRD_TV_Thu),
                itemView.findViewById(R.id.RLRD_TV_Fri),
                itemView.findViewById(R.id.RLRD_TV_Sat)
            };

            container = itemView.findViewById(R.id.RLRD_container);
        }
    }

    @NonNull
    @Override
    public RoutineAdapter.RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout_routine_display, parent, false);

        return new RoutineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineAdapter.RoutineViewHolder holder, int position) {

        WorkoutRoutine routine = WorkoutData.getInstance().routines().get(position);

        String name = routine.name;

        holder.name.setText(name);

        setDaysBackgroundsFromRoutine(holder, position);

        //The reason we're doing lambdas is because we need the routine variable

        holder.dropdownbtn.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.rlrd_dropdown_menu, popup.getMenu());


            //Workout MenuItem
            popup.getMenu().getItem(0).setOnMenuItemClickListener(item -> {

                final Intent intent = new Intent(parentContext, WorkoutModeActivity.class);

                intent.putExtra(Utility.WORKOUT_MODE_BUNDLE_KEY, routine.toJSON().toString());

                ContextCompat.startActivity(parentContext, intent, new Bundle());

                return true;
            });

            //Edit MenuItem
            popup.getMenu().getItem(1).setOnMenuItemClickListener(item -> {

                final Intent intent = new Intent(parentContext, EditWorkoutRoutineActivity.class);

                intent.putExtra(Utility.EDIT_WORKOUT_BUNDLE_KEY, routine.toJSON().toString());

                ContextCompat.startActivity(parentContext, intent, new Bundle());

                return true;
            });

            //Delete MenuItem
            popup.getMenu().getItem(2).setOnMenuItemClickListener(item -> {

                AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);

                builder.setMessage("Are you sure? (This cannot be undone.)");
                builder.setNegativeButton("No", (dialog, which) -> {});

                builder.setPositiveButton("Yes", (dialog, which) -> {

                    if(which != DialogInterface.BUTTON_POSITIVE) return;

                    WorkoutData.getInstance().routines().remove(routine);

                    notifyDataSetChanged();

                    WorkoutData.getInstance().saveData(parentContext);
                });

                builder.show();

                return true;
            });


            popup.show();
        });
    }



    @SuppressLint("NewApi")
    private void setDaysBackgroundsFromRoutine(RoutineAdapter.RoutineViewHolder holder, int index) {

        if(WorkoutData.getInstance().get(index).workoutDays.length == 0) return;

        for(int i = 0; i < holder.days.length; i++){
            set_DaysTV_colorAndBG(holder.days[i], false);
        }

        WorkoutRoutine routine = WorkoutData.getInstance().get(index);
        int i = 0;

        //Long story short: For every day that the user has this routine
        //it will set the corresponding checkbox to true.

        while(i < routine.workoutDays.length){
            switch(routine.workoutDays[i]){
                case MONDAY:
                    set_DaysTV_colorAndBG(holder.days[1], true);
                    break;
                case TUESDAY:
                    set_DaysTV_colorAndBG(holder.days[2], true);
                    break;
                case WEDNESDAY:
                    set_DaysTV_colorAndBG(holder.days[3], true);
                    break;
                case THURSDAY:
                    set_DaysTV_colorAndBG(holder.days[4], true);
                    break;
                case FRIDAY:
                    set_DaysTV_colorAndBG(holder.days[5], true);
                    break;
                case SATURDAY:
                    set_DaysTV_colorAndBG(holder.days[6], true);
                    break;
                case SUNDAY:
                    set_DaysTV_colorAndBG(holder.days[0], true);
                    break;
            }

            i++;
        }


    }


    /** An assisting function that allows us to dynamically change the
     * color of the days TextViews such that they are highlighted
     * when the user has selected that day for their workout routine.
     *
     * @param daysTV The text view apart of the days list we wish to alter
     * @param onThatDay Whether or not the workout is on that day or not
     */
    private void set_DaysTV_colorAndBG(TextView daysTV, boolean onThatDay){

        //Not using application context prevents a really bad bug.

        daysTV.setBackgroundTintList(
            ContextCompat.getColorStateList(parentContext,
            (onThatDay) ? R.color.workoutResult_dateDisplay_BG_true : R.color.workoutResult_dateDisplay_BG_false)
        );

        daysTV.setTextColor(
            ContextCompat.getColor(parentContext,
            (onThatDay) ? R.color.workoutResult_dateDisplay_Text_true : R.color.workoutResult_dateDisplay_Text_false)
        );

    }

    @Override
    public int getItemCount() {

        return WorkoutData.getInstance().length();
    }

}
