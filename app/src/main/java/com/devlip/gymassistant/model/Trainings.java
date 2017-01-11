package com.devlip.gymassistant.model;

import android.provider.BaseColumns;
import com.devlip.gymassistant.database.annotations.Table;
import com.devlip.gymassistant.database.annotations.type.dbString;

@Table(name = "Trainings")
public class Trainings implements BaseColumns {

    @dbString
    public static final String NAME = "name";

    @dbString
    public static final String EXERCISE = "exercise";

}
