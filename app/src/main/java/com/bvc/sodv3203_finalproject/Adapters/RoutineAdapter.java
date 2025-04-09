package com.bvc.sodv3203_finalproject.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.EditWorkoutRoutineActivity;
import com.bvc.sodv3203_finalproject.R;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>{

    public RoutineAdapter(){
    }

    public class RoutineViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        //The array is in the following order:
        //Sun, Mon, Tue, Wed, Thur, Fri, Sat
        private TextView[] days;

        private ImageButton dropdownbtn;

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

                //Temporary:
                Utility.displayMsg(Utility.applicationContext, "Workout Item Clicked", true);

                return true;
            });

            //Edit MenuItem
            popup.getMenu().getItem(1).setOnMenuItemClickListener(item -> {

                //Temporary:
                final Intent intent = new Intent(Utility.applicationContext, EditWorkoutRoutineActivity.class);

                intent.putExtra(Utility.EDIT_WORKOUT_BUNDLE_KEY, routine.toJSON().toString());

                ContextCompat.startActivity(v.getContext(), intent, new Bundle());

                return true;
            });

            //Delete MenuItem
            popup.getMenu().getItem(2).setOnMenuItemClickListener(item -> {

                //Temporary:
                Utility.displayMsg(Utility.applicationContext, "Delete Item Clicked", true);

                return true;
            });


            popup.show();
        });
    }



    @SuppressLint("NewApi")
    private void setDaysBackgroundsFromRoutine(RoutineAdapter.RoutineViewHolder holder, int index) {

        if(WorkoutData.getInstance().get(index).workoutDays.length == 0) return;

        WorkoutRoutine routine = WorkoutData.getInstance().get(index);
        int i = 0;

        //Long story short: For every day that the user has this routine
        //

        while(i < routine.workoutDays.length){
            switch(routine.workoutDays[i]){
                case MONDAY:
                    set_DaysTV_toTrue(holder.days[1]);
                    break;
                case TUESDAY:
                    set_DaysTV_toTrue(holder.days[2]);
                    break;
                case WEDNESDAY:
                    set_DaysTV_toTrue(holder.days[3]);
                    break;
                case THURSDAY:
                    set_DaysTV_toTrue(holder.days[4]);
                    break;
                case FRIDAY:
                    set_DaysTV_toTrue(holder.days[5]);
                    break;
                case SATURDAY:
                    set_DaysTV_toTrue(holder.days[6]);
                    break;
                case SUNDAY:
                    set_DaysTV_toTrue(holder.days[0]);
                    break;
            }

            i++;
        }


    }


    private void set_DaysTV_toTrue(TextView daysTV){

        daysTV.setBackgroundTintList(
            ContextCompat.getColorStateList(Utility.applicationContext, R.color.workoutResult_dateDisplay_BG_true)
        );

        daysTV.setTextColor(
            ContextCompat.getColor(Utility.applicationContext, R.color.workoutResult_dateDisplay_Text_true)
        );

    }

    @Override
    public int getItemCount() {

        return WorkoutData.getInstance().length();
    }

}
