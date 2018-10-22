package com.geekbrains01.sogoodworkout.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geekbrains01.sogoodworkout.Final.Constants;
import com.geekbrains01.sogoodworkout.Model.Workout;
import com.geekbrains01.sogoodworkout.R;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {
    private TextView title, description, recordDate, recordRepsCount, recordWeight;
    CardView cardView;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.list_item_title_text_view);
        description = itemView.findViewById(R.id.list_item_description_text_view);
        recordDate = itemView.findViewById(R.id.list_item_date);
        recordRepsCount = itemView.findViewById(R.id.list_item_reps_textview);
        recordWeight = itemView.findViewById(R.id.list_item_weight_textview);
        cardView = itemView.findViewById(R.id.card_view);
    }

    public void bindView(final Workout workout) {
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordDate.setText(workout.getFormattedRecordDate());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
                intent.putExtra(Constants.WORKOUT_INDEX,workout.getWorkCount()-1);
                context.startActivity(intent);
            }
        });
    }
}