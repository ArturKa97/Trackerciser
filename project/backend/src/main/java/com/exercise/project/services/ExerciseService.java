package com.exercise.project.services;

import com.exercise.project.entities.Exercise;

public interface ExerciseService {
    void addExercise (Exercise exercise);
    Exercise getExerciseById (Long id);
}
