package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;

import java.util.List;

public interface ExerciseService {

    void addExercise(Exercise exercise);
    ExerciseDTO getExerciseById(Long id);
    List<ExerciseDTO> getAllExercises();
    void deleteExerciseById(Long id);
    ExerciseDTO addExerciseToWorkoutSession(Long workoutSessionId, Long exerciseTypeId);
}
