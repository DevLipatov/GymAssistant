package com.devlip.gymassistant.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devlip.gymassistant.App;
import com.devlip.gymassistant.R;
import com.devlip.gymassistant.database.DbHelper;
import com.devlip.gymassistant.model.Approaches;
import com.devlip.gymassistant.model.ExerciseList;
import com.devlip.gymassistant.model.Exercises;
import com.devlip.gymassistant.threads.ResultCallback;

import java.util.ArrayList;
import java.util.List;

public class NewExerciseActivity extends AppCompatActivity {

    DbHelper dbHelper;
    Spinner spinnerExercise;
    String[] exercises;
    Handler handler;
    List<View> allEds;
    int selectedItemReiterations;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        spinnerExercise = (Spinner) findViewById(R.id.spinnerExercise);

        dbHelper = ((App) getApplication()).getDbHelper();
        handler = new Handler();
        sharedPreferences = getSharedPreferences("TablesSizes", Context.MODE_PRIVATE);

        allEds = new ArrayList<>();

        setSpinner();

        spinnerExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String category = spinnerExercise.getSelectedItem().toString().replaceAll("\\s+", "");
                final String query = "SELECT * FROM " + DbHelper.getTableName(ExerciseList.class) + " WHERE name_code= '" + category + "'";
                getQuery(query, new ResultCallback<Cursor>() {
                    @Override
                    public void onSuccess(Cursor result) {
                        result.moveToFirst();
                        selectedItemReiterations = result.getInt(result.getColumnIndex(ExerciseList.APPROACH_COUNT));
                        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearApproaches);
                        linearLayout.removeAllViews();
                        for (int i = 0; i < selectedItemReiterations; i++) {
                            final View view = getLayoutInflater().inflate(R.layout.custom_edittext_layout, null);
                            TextView textView = (TextView) view.findViewById(R.id.textView);
                            textView.setText("Approach #" + (i + 1) + " :");
                            allEds.add(view);
                            linearLayout.addView(view);
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    private void getQuery(final String query, final ResultCallback<Cursor> resultCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Cursor cursor = dbHelper.query(query);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultCallback.onSuccess(cursor);
                    }
                });
            }
        }).start();
    }

    //spinner items from cursor
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
        final String query = "SELECT name FROM " + DbHelper.getTableName(ExerciseList.class);
        getQuery(query, new ResultCallback<Cursor>() {
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

    public void onSaveExerciseClick(View view) {
        for (int i = 0; i< allEds.size(); i++) {
            final ContentValues contentValues = new ContentValues();
            contentValues.put(Approaches.REITERATIONS,
                    Integer.valueOf (((EditText) allEds.get(i).findViewById(R.id.editReiterationsCurrent)).getText().toString()));
            contentValues.put(Approaches.WEIGHT,
                    Long.valueOf (((EditText) allEds.get(i).findViewById(R.id.editWeightCurrent)).getText().toString()));
            contentValues.put(Approaches.APPROACH, (i+1));
            //TODO think how to save this better
            contentValues.put(Approaches.EXERCISE_ID, sharedPreferences.getInt("Exercises"+1, 0));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dbHelper.insert(Approaches.class, contentValues);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();

        }
    }
}
