package com.bvc.sodv3203_finalproject.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.EditWorkoutRoutineActivity;
import com.bvc.sodv3203_finalproject.R;
import com.bvc.sodv3203_finalproject.util.Utility;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutData;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

public class EditWorkoutAdapter extends RecyclerView.Adapter<EditWorkoutAdapter.WorkoutViewHolder>{

    EditWorkoutRoutineActivity parentActivity;

    public EditWorkoutAdapter( EditWorkoutRoutineActivity activity) {
        this.parentActivity = activity;
    }


    public class WorkoutViewHolder extends RecyclerView.ViewHolder{

        private WorkoutRoutine sourceData;

        private TextView workoutName, tgtMuscle;
        private EditText setCount, repCount;

        public WorkoutViewHolder(@NonNull View itemView, WorkoutRoutine w) {
            super(itemView);

            this.sourceData = w;


            //RLEWD is meant to be an acronym for Recycle Layout Edit Workout Display
            //I apologize for any correlation to any inappropriate wording or phrase present

            workoutName = itemView.findViewById(R.id.RLEWD_workoutTitle);
            tgtMuscle = itemView.findViewById(R.id.RLEWD_targetMuscle);

            setCount = itemView.findViewById(R.id.RLEWD_ET_sets);
            repCount = itemView.findViewById(R.id.RLEWD_ET_reps);

        }

    }


    @NonNull
    @Override
    public EditWorkoutAdapter.WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout_editworkout_display, parent, false);

        return new EditWorkoutAdapter.WorkoutViewHolder(itemView, parentActivity.editedRoutine);
    }

    @Override
    public void onBindViewHolder(@NonNull EditWorkoutAdapter.WorkoutViewHolder holder, int position) {
        Workout w = holder.sourceData.workouts.get(position);

        holder.workoutName.setText(w.name);
        holder.tgtMuscle.setText(w.targetMuscle.name().toUpperCase());

        holder.setCount.setText(String.format("%d", w.sets));
        holder.repCount.setText(String.format("%d", w.reps));

        holder.setCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String data = s.toString().trim();

                if(data.isEmpty()) return;

                if(data.contains("-")){
                    Utility.displayMsg(parentActivity, "Set # cannot be negative", false);
                    return;
                }

                try{
                    //We do this seperately to prevent any accidental changes.

                    int newSets = Integer.parseInt(data);

                    //A direct change to the source routine.
                    parentActivity.editedRoutine.workouts.get(holder.getAdapterPosition()).sets = newSets;

                } catch (NumberFormatException e) {
                    Utility.displayMsg(parentActivity, "Only enter numbers for the set count!", false);

                    return;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return parentActivity.editedRoutine.getWorkouts().size();
    }

}
