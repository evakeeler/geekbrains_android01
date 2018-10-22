package com.geekbrains01.sogoodworkout.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.geekbrains01.sogoodworkout.R;


public class WorkoutCardviewList extends ListActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = new String[]{"The Muscle.", "Strength Training The Complete 4.", "Week Beginner's Workout.",
                "Workout With Keto Ribeye.", "Top Bodyweight Back Exercises at Home.",
                "Standing abs workout. Forget everything you know about abdominal exercises.",
                "5 Exercises for the Perfect Beginner Bodyweight Workout.",
                "Bodyweight Workout: Meet Your Moves. These five must-do exercises.",
                "Top-notch workout exercises, routines, and info! Anything you need to get strong."};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_cardview_list, R.id.list_item_description_text_view, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " = this item was selected", Toast.LENGTH_LONG).show();
    }
}
