package com.devlip.gymassistant.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.devlip.gymassistant.R;
import com.devlip.gymassistant.activity.NewTrainingActivity;
import com.devlip.gymassistant.adapters.ExpandableListAdapter;

import java.util.HashMap;
import java.util.List;

public class TrainingListFragment extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training_list, null);

        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);
        Button btnAdd = (Button) v.findViewById(R.id.btnAddTraining);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewTrainingActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
