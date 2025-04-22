package com.bvc.sodv3203_finalproject.Adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.AddWorkoutFromSearchActivity;
import com.bvc.sodv3203_finalproject.R;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;


//We just extend the original class so that we don't reinvent the wheel.
public class AddSearchWorkoutToRoutineAdapter extends RoutineAdapter {

    protected Workout workoutToBeAdded;
    private AddWorkoutFromSearchActivity parent;

    public AddSearchWorkoutToRoutineAdapter(AddWorkoutFromSearchActivity parent, @NonNull Workout w) {
        super(parent);

        //While we already have the parentContext, we need to access
        //some specific variables from the parent class.
        this.parent = parent;
        this.workoutToBeAdded = w;
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        //Don't want the user to start an infinite chain of...activities.
        holder.dropdownbtn.setVisibility(View.INVISIBLE);
        holder.dropdownbtn.setClickable(false);

        holder.container.setOnClickListener(v -> {

            try{

                int sets = Integer.parseInt(Utility.getText(parent.setsInput));
                int reps = Integer.parseInt(Utility.getText(parent.repsInput));

                if(sets == 0 || reps == 0){
                    Utility.displayMsg(parent, Utility.getErrorMessage(parent, R.string.ErrMsg_setsOrRepsAre0), false);

                    return;
                }

                WorkoutData.getInstance().get(position).add(new Workout(
                        workoutToBeAdded.name,
                        sets,
                        reps,
                        workoutToBeAdded.targetMuscle
                ));


            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            ((AppCompatActivity) parentContext).finish();
        });

    }
}
