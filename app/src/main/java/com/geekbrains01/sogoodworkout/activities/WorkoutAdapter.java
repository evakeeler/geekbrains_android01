package com.geekbrains01.sogoodworkout.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekbrains01.sogoodworkout.Model.Workout;
import com.geekbrains01.sogoodworkout.Model.WorkoutList;
import com.geekbrains01.sogoodworkout.R;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
    List<Workout> workoutList = WorkoutList.getInstance().getWorkouts();

//    public WorkoutAdapter(List<Workout> workoutList) {
//        this.workoutList = workoutList;
//    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.activity_cardview_list, viewGroup, false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder workoutViewHolder,int index) {
        workoutViewHolder.bindView(workoutList.get(index));
    }

    @Override
    public int getItemCount() {
        return workoutList != null ? workoutList.size() : 0;
    }

    public void removeItem(int position) {
        workoutList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Workout workout, int position) {
        workoutList.add(position, workout);
        // notify item added by position
        notifyItemInserted(position);
    }
}