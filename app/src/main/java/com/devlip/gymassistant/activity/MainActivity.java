package com.devlip.gymassistant.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.devlip.gymassistant.App;
import com.devlip.gymassistant.R;
import com.devlip.gymassistant.database.DbHelper;
import com.devlip.gymassistant.fragment.ExerciseListFragment;
import com.devlip.gymassistant.fragment.TrainingListFragment;

public class MainActivity extends AppCompatActivity {

    ExerciseListFragment exerciseListFragment;
    TrainingListFragment trainingListFragment;
    FragmentTransaction fragmentTransaction;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = ((App) getApplication()).getDbHelper();
        exerciseListFragment = new ExerciseListFragment();
        trainingListFragment = new TrainingListFragment();
    }

    public void onClickFrag1(View view) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgmContainer, trainingListFragment);
        fragmentTransaction.commit();
    }

    public void onClickFrag2(View view) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgmContainer, trainingListFragment);
        fragmentTransaction.commit();
    }
}
