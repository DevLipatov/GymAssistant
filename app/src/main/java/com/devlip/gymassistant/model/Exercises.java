package com.devlip.gymassistant.model;

import android.provider.BaseColumns;
import com.devlip.gymassistant.database.annotations.Table;
import com.devlip.gymassistant.database.annotations.type.dbInteger;
import com.devlip.gymassistant.database.annotations.type.dbString;

@Table(name = "Exercises")
public class Exercises implements BaseColumns {

    @dbString
    public static final String NAME = "name";

    @dbInteger
    public static final String APPROACH = "approach";

    @dbInteger
    public static final String REITERATIONS = "reiterations";

    @dbInteger
    public static final String WEIGHT = "weight";
}
