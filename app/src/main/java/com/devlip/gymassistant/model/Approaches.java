package com.devlip.gymassistant.model;

import android.provider.BaseColumns;
import com.devlip.gymassistant.database.annotations.Table;
import com.devlip.gymassistant.database.annotations.type.dbInteger;
import com.devlip.gymassistant.database.annotations.type.dbLong;

@Table(name = "Approaches")
public class Approaches implements BaseColumns {

    @dbInteger
    public static final String EXERCISE_ID = "exercise_id";

    @dbInteger
    public static final String APPROACH = "approach";

    @dbLong
    public static final String WEIGHT = "weight";

    @dbInteger
    public static final String REITERATIONS = "reiterations";
}
