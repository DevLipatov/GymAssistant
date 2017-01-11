package com.devlip.gymassistant.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.devlip.gymassistant.R;

import java.util.Calendar;

public class NewTrainingActivity extends AppCompatActivity {

    private Calendar calendar = Calendar.getInstance();
    EditText editDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training);

        editDate = (EditText) findViewById(R.id.editTrainingDate);
    }

    public void setDate(View v) {
        new DatePickerDialog(NewTrainingActivity.this, listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void setInitialDateTime() {
        editDate.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    public void onAddExercise(View view) {
        Intent intent = new Intent(getApplicationContext(), NewExerciseActivity.class);
        //TODO move from there
        startActivityForResult(intent, 90);
    }
}
