package com.geekbrains01.sogoodworkout.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public final class WorkoutList {
    private static final WorkoutList ourInstance = new WorkoutList();
    private List<Workout> workouts;
    private Random rnd = new Random();

    public static WorkoutList getInstance(){
        return ourInstance;
    }

    private WorkoutList(){
        workouts = new ArrayList<>();
        for (int i = 1; i <41; i++){
            Workout workout = new Workout("Новое упражнение №"+i);
            workout.setDescription("Большое описание упражнения и как его делать №"+i);
            workout.setRecordDate(new Date());
            workout.setRecordRepsCount(rnd.nextInt(201));
            workout.setRecordWeight(rnd.nextInt(151));
            workout.setWorkCount(i);
            workouts.add(workout);
        }
    }

    public List<Workout> getWorkouts(){
        return workouts;
    }

    public void addWorkouts(){
        Workout workout = new Workout("Новое упражнение №"+(workouts.size()+1));
        workout.setDescription("Большое описание упражнения и как его делать №"+(workouts.size()+1));
        workout.setRecordDate(new Date());
        workout.setRecordRepsCount(rnd.nextInt(201));
        workout.setRecordWeight(rnd.nextInt(151));
        workout.setWorkCount(workouts.size()+1);
        workouts.add(workout);
    }

}
