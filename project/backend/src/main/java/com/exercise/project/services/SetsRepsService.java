package com.exercise.project.services;

import com.exercise.project.entities.SetsReps;

public interface SetsRepsService {
    SetsReps addInfoToExercise(SetsReps setsReps, Long exerciseId);
    void removeInfoById(Long setsRepsId);
    SetsReps updateExerciseInfoById(SetsReps updatedSetsReps, Long setsRepsId);
}
