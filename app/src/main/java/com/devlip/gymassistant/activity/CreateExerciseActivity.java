package com.devlip.gymassistant.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.devlip.gymassistant.App;
import com.devlip.gymassistant.R;
import com.devlip.gymassistant.database.DbHelper;
import com.devlip.gymassistant.model.ExerciseList;

public class CreateExerciseActivity extends AppCompatActivity {

    EditText editExerciseName;
    EditText editApproachCount;
    EditText editComment;
    RadioGroup rgroupWeight;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    DbHelper dbHelper;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        editExerciseName = (EditText) findViewById(R.id.editExerciseName);
        editApproachCount = (EditText) findViewById(R.id.editApproachCount);
        editComment = (EditText) findViewById(R.id.editComment);
        rgroupWeight = (RadioGroup) findViewById(R.id.rgroupWeight);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio1.setChecked(true);

        dbHelper = ((App) getApplication()).getDbHelper();
        handler = new Handler();

        setRadioGroupListener();
    }

    private Boolean checkFields() {
        return editComment.getText().toString().isEmpty() || editApproachCount.getText().toString().isEmpty();
    }

    private void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wrong data");
        builder.setMessage("Fill all main fields");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onSaveExerciseClick(View view) {
        if (checkFields()) {
            showError();
        } else {
            final ContentValues contentValues = new ContentValues();
            contentValues.put(ExerciseList.NAME, editExerciseName.getText().toString());
            contentValues.put(ExerciseList.TYPE, rgroupWeight.getCheckedRadioButtonId());
            contentValues.put(ExerciseList.APPROACH_COUNT, Integer.valueOf(editApproachCount.getText().toString()));
            contentValues.put(ExerciseList.COMMENT, editComment.getText().toString());
            contentValues.put(ExerciseList.NAME_CODE, editExerciseName.getText().toString().replaceAll("\\s+",""));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final long id = dbHelper.insert(ExerciseList.class, contentValues);
                    if (id > 0) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    public void onCancelExerciseClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit without save?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setRadioGroupListener() {
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio1.setChecked(true);
                radio2.setChecked(false);
                radio3.setChecked(false);
            }
        });

        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio1.setChecked(false);
                radio2.setChecked(true);
                radio3.setChecked(false);
            }
        });

        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio1.setChecked(false);
                radio2.setChecked(false);
                radio3.setChecked(true);
            }
        });
    }
}
