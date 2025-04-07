package com.bvc.sodv3203_finalproject.workouts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bvc.sodv3203_finalproject.R;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>{

    public RoutineAdapter(){
    }

    public class RoutineViewHolder extends RecyclerView.ViewHolder{

        private TextView name;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.RLRD_TV_name);

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

        String name = WorkoutData.getInstance().routines().get(position).name;

        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {

        return WorkoutData.getInstance().length();
    }

}
