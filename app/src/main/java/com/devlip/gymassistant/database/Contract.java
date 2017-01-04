package com.devlip.gymassistant.database;

import com.devlip.gymassistant.model.ExerciseList;
import com.devlip.gymassistant.model.Exercises;
import com.devlip.gymassistant.model.Training;

public final class Contract {

    public static final Class<?>[] MODELS =
            {
                    ExerciseList.class,
                    Exercises.class,
                    Training.class
            };
}
