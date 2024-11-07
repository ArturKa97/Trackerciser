package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;

import java.util.List;

public interface WorkoutSessionService {
    WorkoutSession addWorkoutSession(WorkoutSession workoutSession);

    WorkoutSessionDTO getWorkoutSessionById(Long id);

    List<WorkoutSessionDTO> getAllWorkoutSessions();

    void deleteWorkoutSessionById(Long id);

    WorkoutSession updateWorkoutSessionById(Long workoutSessionId, WorkoutSession updatedWorkoutSession);
}
