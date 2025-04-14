package com.bvc.sodv3203_finalproject.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bvc.sodv3203_finalproject.AddWorkoutFromSearchActivity;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;


//We just extend the original class so that we don't reinvent the wheel.
public class AddRoutineAdapter extends RoutineAdapter {

    protected Workout workoutToBeAdded;
    private AddWorkoutFromSearchActivity parent;

    public AddRoutineAdapter(AddWorkoutFromSearchActivity parent, @NonNull Workout w) {
        super(parent);

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

                int sets = Integer.parseInt(parent.setsInput.getText().toString());
                int reps = Integer.parseInt(parent.repsInput.getText().toString());

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
