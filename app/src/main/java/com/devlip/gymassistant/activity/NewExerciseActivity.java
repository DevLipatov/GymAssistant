package com.devlip.gymassistant.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.devlip.gymassistant.App;
import com.devlip.gymassistant.R;
import com.devlip.gymassistant.database.DbHelper;
import com.devlip.gymassistant.model.ExerciseList;
import com.devlip.gymassistant.model.Exercises;
import com.devlip.gymassistant.threads.ResultCallback;

public class NewExerciseActivity extends AppCompatActivity {

    DbHelper dbHelper;
    Spinner spinnerExercise;
    String[] exercises;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        spinnerExercise = (Spinner) findViewById(R.id.spinnerExercise);

        dbHelper = ((App) getApplication()).getDbHelper();
        handler = new Handler();

        setSpinner();

    }

    @Override
    protected void onResume() {
        setSpinner();
        super.onResume();
    }

    public void onCreateExercise(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateExerciseActivity.class);
        startActivity(intent);
    }

    private void getExercises(final ResultCallback<Cursor> resultCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Cursor cursor = dbHelper.query("SELECT name FROM " + DbHelper.getTableName(ExerciseList.class));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultCallback.onSuccess(cursor);
                    }
                });
            }
        }).start();
    }

    private String[] setExercises(Cursor cursor) {
        String[] exercises = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            exercises[i] = cursor.getString(cursor.getColumnIndex(Exercises.NAME));
            cursor.moveToNext();
        }
        cursor.close();
        return exercises;
    }

    private void setSpinner() {
        getExercises(new ResultCallback<Cursor>() {
            @Override
            public void onSuccess(Cursor result) {
                exercises = setExercises(result);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, exercises);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerExercise.setAdapter(adapter);
                spinnerExercise.setSelection(0);
            }
        });
    }
}
