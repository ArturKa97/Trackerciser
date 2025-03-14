package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.entities.ExerciseSet;

public interface ExerciseSetService {
    ExerciseSetDTO addExerciseSetToExercise(ExerciseSetDTO exerciseSetDTO, Long exerciseId);
    void removeExerciseSetById(Long exerciseSetId);
    ExerciseSetDTO updateExerciseSetById(ExerciseSetDTO updatedExerciseSet, Long exerciseSetId);
}
