package com.exercise.project.services;

import com.exercise.project.entities.Exercise;

import java.util.List;

public interface ExerciseService {
    void addExercise (Exercise exercise);
    Exercise getExerciseById (Long id);
    List<Exercise> getAllExercises();
    void deleteExerciseById(Long id);
    void addExerciseToWorkoutSession(Long workoutSessionId, Long exerciseTypeId);
}
