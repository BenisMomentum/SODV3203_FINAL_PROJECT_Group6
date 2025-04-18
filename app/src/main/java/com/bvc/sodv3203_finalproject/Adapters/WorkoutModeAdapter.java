package com.bvc.sodv3203_finalproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bvc.sodv3203_finalproject.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.WorkoutModeActivity;
import com.bvc.sodv3203_finalproject.workouts.Workout;
import com.bvc.sodv3203_finalproject.workouts.WorkoutRoutine;

import java.util.Locale;

public class WorkoutModeAdapter extends RecyclerView.Adapter<WorkoutModeAdapter.WorkoutModeViewHolder>{


    public WorkoutRoutine parentRoutine;

    public WorkoutModeActivity parentActivity;

    public WorkoutModeAdapter(WorkoutModeActivity parent){
        this.parentActivity = parent;
        this.parentRoutine = parent.selectedRoutine;
    }

    public class WorkoutModeViewHolder extends RecyclerView.ViewHolder {

        public WorkoutRoutine sourceData;


        public int sets = 0, reps = 0;

        //We use this to assist us with bounds checking
        //The MAX_SETS variable is set to the current set count
        //of the workout when it is retrieved.
        public static final int MIN_SETS = 0;

        public TextView setsText, repsText, title;

        public ImageButton incSet, decSet;

        //Boiler plate assistance.
        public final String SETS_PREFIX = "Sets: ";
        public final String REPS_PREFIX = "Reps: ";

        public WorkoutModeViewHolder(@NonNull View itemView, @NonNull WorkoutRoutine src) {
            super(itemView);

            //The source data is used to get the exact
            //workout we will generate.
            this.sourceData = src;

            this.setsText = itemView.findViewById(R.id.RLWMD_TV_sets);
            this.repsText = itemView.findViewById(R.id.RLWMD_TV_reps);
            this.title = itemView.findViewById(R.id.RLWMD_workoutTitle);

            this.incSet = itemView.findViewById(R.id.RLWMD_ibtn_incSet);
            this.decSet = itemView.findViewById(R.id.RLWMD_ibtn_decSet);
        }

        public void updateSetText(){
            if(setsText == null) return;

            this.setsText.setText(String.format(Locale.getDefault(),"%s%d", SETS_PREFIX, sets));

        }

        public void updateRepText(){
            if(repsText == null) return;

            this.repsText.setText(String.format(Locale.getDefault(),"%s%d", REPS_PREFIX, reps));
        }
    }

    @NonNull
    @Override
    public WorkoutModeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout_workoutmode_display, parent, false);

        return new WorkoutModeAdapter.WorkoutModeViewHolder(itemView, parentActivity.selectedRoutine);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutModeViewHolder holder, int position) {

        Workout w = holder.sourceData.get(position);

        final int MAX_SETS = w.sets;

        holder.sets = w.sets;
        holder.reps = w.reps;

        holder.title.setText(w.name);
        holder.updateSetText();
        holder.updateRepText();

        holder.incSet.setOnClickListener(v -> {

            if(holder.sets < MAX_SETS){
                holder.sets++;
                holder.updateSetText();
            }

        });

        holder.decSet.setOnClickListener(v -> {
            if(holder.sets - 1 >= WorkoutModeViewHolder.MIN_SETS){
                holder.sets--;
                holder.updateSetText();
            }
        });


    }

    @Override
    public int getItemCount() {
        return parentActivity.selectedRoutine.workouts.size();
    }

}
