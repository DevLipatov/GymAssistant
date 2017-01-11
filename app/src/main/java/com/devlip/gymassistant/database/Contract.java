package com.devlip.gymassistant.database;

import com.devlip.gymassistant.model.Approaches;
import com.devlip.gymassistant.model.ExerciseList;
import com.devlip.gymassistant.model.Exercises;
import com.devlip.gymassistant.model.TrainingList;
import com.devlip.gymassistant.model.Trainings;

public final class Contract {

    public static final Class<?>[] MODELS =
            {
                    ExerciseList.class,
                    Exercises.class,
                    Trainings.class,
                    Approaches.class,
                    TrainingList.class
            };
}
