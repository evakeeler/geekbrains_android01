package com.geekbrains01.sogoodworkout.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.ActivityInfo;

import com.geekbrains01.sogoodworkout.Final.Constants;
import com.geekbrains01.sogoodworkout.Model.Workout;
import com.geekbrains01.sogoodworkout.Model.WorkoutList;
import com.geekbrains01.sogoodworkout.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class WorkoutDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView recordDate, recordRepsCount, recordWeight;
    private TextView labelRecord, labelKg, labelDate, labelRepsCount, labelWeight, labelSettings,labelLocalRU,labelLocalEN;
    private TextView labelChooseWeight, labelChooseKg;
    private TextView description, weight;
    private ImageView image;
    private SeekBar weightSeekBar;
    private EditText repsCountEditText;
    private Button saveRecordButton;
    private Locale mNewLocale;
    private final static String TAG = "WorkoutDA_Log";
    private final static String REPS_COUNT = "0";
    private final static String WEIGHT_MASS = "0";

    private SharedPreferences sp;
    private HashSet<String> dataSet = new HashSet<>();
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Вызван метод onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Workout workout = new Workout("Подтягивания", "Подтягивания на перекладине", 0, new Date(), 0,1);
        Intent intent = getIntent();
        int index = intent.getIntExtra(Constants.WORKOUT_INDEX,0);
        //Workout workout = WorkoutList.getInstance().getWorkouts().get(getIntent().getIntExtra("hhh",0));
        Workout workout = WorkoutList.getInstance().getWorkouts().get(index);
        initGUI(workout);
        addListeners();

        saveRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDataRecord();
            }
        });
        sp = getSharedPreferences("strSetKey", Context.MODE_PRIVATE);
        if (sp.contains("strSetKey")) {
            Set<String> ret = sp.getStringSet("strSetKey", new HashSet<String>());
            dataSet = new HashSet<>(ret);
            for (String r : ret) {
                createSavedRecord(r);
            }
        }
        lookForMaxRecord();
        loadSaveInstanceState(savedInstanceState);
    }

    private void lookForMaxRecord() {
        for (String r : dataSet) {
            data = r.split("_");
            int repsCompareNew = Integer.parseInt(data[1]);
            int repsCompare = Integer.parseInt(recordRepsCount.getText().toString());
            if (repsCompareNew > repsCompare) {
                recordRepsCount.setText(data[1]);
                recordWeight.setText(data[2]);
                recordDate.setText(data[0]);
            }
        }
    }

    private void doDataRecord() {
        if (!repsCountEditText.getText().toString().equals("")) {
            if (Integer.parseInt(repsCountEditText.getText().toString()) > 0 && Integer.parseInt(weight.getText().toString()) > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ROOT);
                String saveNewRecord = sdf.format(new Date()) + "_" + repsCountEditText.getText().toString() + "_" + weight.getText().toString();
                dataSet.add(saveNewRecord);
                SharedPreferences.Editor e = sp.edit();
                e.putStringSet("strSetKey", dataSet);
                e.apply();
                createSavedRecord(saveNewRecord);
                lookForMaxRecord();
            }
        }
        showMsg("Ваш Вес или Количество повторов не указаны!");
    }

    private void showMsg(String msg){
        Toast toast = Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    private void createSavedRecord(String rec) {
        data = rec.split("_");
        LinearLayout mainLayout = findViewById(R.id.layout_main);
        LinearLayout recordLayout = new LinearLayout(this);
        recordLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams recordLayoutLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        recordLayout.setLayoutParams(recordLayoutLayoutParams);

        LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int x = 0; x < data.length; x++) {
            TextView tv = new TextView(this);
            switch (x) {
                case 0:
                    tv.setText(labelDate.getText().toString() + data[0]);
                    break;
                case 1:
                    tv.setText(labelRepsCount.getText().toString() + data[1]);
                    break;
                case 2:
                    tv.setText(labelWeight.getText().toString() + data[2] + labelKg.getText().toString());
                    break;
            }
            recordLayout.addView(tv, lpView);
        }
        View dividerGenerate = new View(this);
        dividerGenerate.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
        dividerGenerate.setBackgroundColor(Color.rgb(121, 134, 203));
        recordLayout.addView(dividerGenerate);
        mainLayout.addView(recordLayout, 3);
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"Вызван метод onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"Вызван метод onDestroy");
        super.onDestroy();
    }

    private void loadSaveInstanceState(Bundle savedState){
        if (savedState != null && savedState.containsKey(REPS_COUNT)){
            repsCountEditText.setText(savedState.getString(REPS_COUNT));
            weight.setText(savedState.getString(WEIGHT_MASS));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG,"Вызван метод onSaveInstanceState");
        outState.putString(REPS_COUNT,repsCountEditText.getText().toString());
        outState.putString(WEIGHT_MASS,weight.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"Вызван метод onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"Вызван метод onResume");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showMsg("Меню настройки в разработке");
            return true;
        }

        if (id == R.id.localization_ru) {
            setLocale("ru");
            return true;
        }

        if (id == R.id.localization_en) {
            setLocale("en");
            return true;
        }

        if (id == R.id.orientation_port) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return true;
        }

        if (id == R.id.orientation_land) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addListeners() {
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                weight.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initGUI(Workout workout) {
        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedRecordDate());
        recordRepsCount = findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        labelRecord = findViewById(R.id.workout_detail_label_record);
        labelKg = findViewById(R.id.workout_detail_label_kg);
        labelDate = findViewById(R.id.workout_detail_label_date);
        labelRepsCount = findViewById(R.id.workout_detail_label_reps_count);
        labelWeight = findViewById(R.id.workout_detail_label_weight);
        labelSettings = findViewById(R.id.action_settings);
        labelLocalRU = findViewById(R.id.localization_ru);
        labelLocalEN = findViewById(R.id.localization_en);
        labelChooseWeight = findViewById(R.id.workout_detail_choose_weight);
        labelChooseKg = findViewById(R.id.workout_detail_choose_kg);

        description = findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());

        weight = findViewById(R.id.workout_detail_weight);
        weightSeekBar = findViewById(R.id.workout_detail_weight_seek_bar);
        repsCountEditText = findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = findViewById(R.id.workout_detail_save_button);
    }

    private void setLocale(String mLang) {
        mNewLocale = new Locale(mLang);
        Locale.setDefault(mNewLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = mNewLocale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        updateTextView();
    }

    private void updateTextView(){
        title.setText(getResources().getString(R.string.app_name));
        labelSettings.setText(getResources().getString(R.string.action_settings));
        labelLocalRU.setText(getResources().getString(R.string.local_ru));
        labelLocalEN.setText(getResources().getString(R.string.local_en));
        labelRecord.setText(getResources().getString(R.string.label_record));
        labelKg.setText(getResources().getString(R.string.kg));
        labelDate.setText(getResources().getString(R.string.date_text));
        labelRepsCount.setText(getResources().getString(R.string.reps_count_text));
        labelWeight.setText(getResources().getString(R.string.weight_text));
        labelChooseWeight.setText(getResources().getString(R.string.weight_text));
        labelChooseKg.setText(getResources().getString(R.string.kg));
        repsCountEditText.setHint(getResources().getString(R.string.reps_count_text));
        saveRecordButton.setText(getResources().getString(R.string.save));
    }

}
