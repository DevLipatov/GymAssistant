package com.devlip.gymassistant.model;

import android.provider.BaseColumns;
import com.devlip.gymassistant.database.annotations.Table;
import com.devlip.gymassistant.database.annotations.type.dbString;

@Table(name = "TrainingList")
public class TrainingList implements BaseColumns {

    @dbString
    public static final String NAME = "name";

    @dbString
    public static final String COMMENT = "comment";
}
