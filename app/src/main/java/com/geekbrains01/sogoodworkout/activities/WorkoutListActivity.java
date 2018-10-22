package com.geekbrains01.sogoodworkout.activities;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekbrains01.sogoodworkout.Final.Constants;
import com.geekbrains01.sogoodworkout.Model.Workout;
import com.geekbrains01.sogoodworkout.Model.WorkoutList;
import com.geekbrains01.sogoodworkout.R;
import android.support.design.widget.CoordinatorLayout;
import com.geekbrains01.sogoodworkout.activities.RecyclerItemTouchHelper;

import java.util.List;

public class WorkoutListActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{
//    Button buttonPullUps;
//    Button buttonPushUps;
//    Button buttonSquats;
//
//    private void initGUI() {
//        buttonPullUps = findViewById(R.id.button_pull_up);
//        buttonPushUps = findViewById(R.id.button_push_up);
//        buttonSquats = findViewById(R.id.button_squats);
//
//        buttonPullUps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent startWorkoutDetailActivity =
//                        new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
//                startActivity(startWorkoutDetailActivity);
//            }
//        });
//
//        buttonSquats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent startWorkoutDetailActivity =
//                        new Intent(WorkoutListActivity.this, WorkoutCardviewList.class);
//                startActivity(startWorkoutDetailActivity);
//            }
//        });
//
//
//    }

    RecyclerView recyclerView;
    WorkoutAdapter adapter;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_list);
        adapter = new WorkoutAdapter();
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

//        Button btn = new Button(this);
//        btn.setText("Добавить упражнение");
//        btn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WorkoutList.getInstance().addWorkouts();
//                adapter.notifyItemInserted(WorkoutList.getInstance().getWorkouts().size());
//                recyclerView.scrollToPosition(WorkoutList.getInstance().getWorkouts().size());
//            }
//        });
//        recyclerView.addView(btn);


        //initGUI();
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof WorkoutViewHolder) {
            String name = WorkoutList.getInstance().getWorkouts().get(viewHolder.getAdapterPosition()).getTitle();
            final Workout deletedItem = WorkoutList.getInstance().getWorkouts().get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            adapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar
                    .make(getWindow().getDecorView().getRootView(), name + " Удалено!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
    

}
