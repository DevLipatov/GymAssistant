package com.devlip.gymassistant.model;

import android.provider.BaseColumns;

import com.devlip.gymassistant.database.annotations.Table;
import com.devlip.gymassistant.database.annotations.type.dbInteger;
import com.devlip.gymassistant.database.annotations.type.dbLong;
import com.devlip.gymassistant.database.annotations.type.dbString;

@Table(name = "Exercises")
public class Exercises implements BaseColumns {

    @dbString
    public static final String NAME = "name";

    @dbInteger
    public static final String APPROACH = "approach";

    @dbInteger
    public static final String REITERATIONS = "reiterations";

    @dbLong
    public static final String WEIGHT = "weight";

    @dbInteger
    public static final String TRAINING_ID = "training_id";

    @dbInteger
    public static final String EXERCISE_ID = "exercise_id";
}
