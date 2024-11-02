package com.exercise.project.services;

import com.exercise.project.entities.ExerciseSet;

public interface ExerciseSetService {
    ExerciseSet addExerciseSetToExercise(ExerciseSet exerciseSet, Long exerciseId);
    void removeExerciseSetById(Long exerciseSetId);
    ExerciseSet updateExerciseSetById(ExerciseSet updatedExerciseSet, Long exerciseSetId);
}
