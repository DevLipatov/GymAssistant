package com.devlip.gymassistant.model;

import android.provider.BaseColumns;

import com.devlip.gymassistant.database.annotations.Table;
import com.devlip.gymassistant.database.annotations.type.dbInteger;
import com.devlip.gymassistant.database.annotations.type.dbString;

@Table(name = "ExerciseList")
public class ExerciseList implements BaseColumns {

    @dbString
    public static final String NAME = "name";

    @dbInteger
    public static final String TYPE = "type";

    @dbInteger
    public static final String APPROACH_COUNT = "approach_count";

    @dbString
    public static final String COMMENT = "comment";

    @dbString
    public static final String NAME_CODE = "name_code";
}
