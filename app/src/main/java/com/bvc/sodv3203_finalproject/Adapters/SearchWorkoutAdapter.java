package com.bvc.sodv3203_finalproject.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.AddWorkoutFromSearchActivity;
import com.bvc.sodv3203_finalproject.R;
import com.bvc.sodv3203_finalproject.SearchWorkoutActivity;
import com.bvc.sodv3203_finalproject.workouts.Workout;

public class SearchWorkoutAdapter extends RecyclerView.Adapter<SearchWorkoutAdapter.SearchWorkoutViewHolder> {

    //The parent activity is used to acquire the resultList
    //so that we can display each workout properly
    private final SearchWorkoutActivity parentActivity;

    public SearchWorkoutAdapter(SearchWorkoutActivity parent){
        this.parentActivity = parent;
    }

    public class SearchWorkoutViewHolder extends RecyclerView.ViewHolder{

        private TextView name, targetMuscle;

        private ImageButton ibtn_addToRoutine;

        public SearchWorkoutViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.RLW_workoutTitle);
            this.targetMuscle = itemView.findViewById(R.id.RLW_workoutTarget);

            this.ibtn_addToRoutine = itemView.findViewById(R.id.RLW_ibtn_AddToRoutine);
        }
    }

    @NonNull
    @Override
    public SearchWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_layout_workout_display, parent, false);

        return new SearchWorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchWorkoutViewHolder holder, int position) {

        Workout w = parentActivity.resultList.get(position);

        holder.name.setText(w.name);
        holder.targetMuscle.setText(w.targetMuscle.name().toUpperCase());

        holder.ibtn_addToRoutine.setOnClickListener(v -> {

            final Intent intent = new Intent(parentActivity, AddWorkoutFromSearchActivity.class);

            intent.putExtra(AddWorkoutFromSearchActivity.WORKOUT_BUNDLE_KEY, w.toJSON().toString());

            parentActivity.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return parentActivity.resultList.size();
    }

}
