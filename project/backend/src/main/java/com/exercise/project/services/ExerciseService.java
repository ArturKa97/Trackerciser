package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;

import java.util.List;

public interface ExerciseService {

    void addExercise(Exercise exercise);
    ExerciseDTO getExerciseById(Long id);
    List<Exercise> getAllExercises();
    void deleteExerciseById(Long id);
    Exercise addExerciseToWorkoutSession(Long workoutSessionId, Long exerciseTypeId);
}
