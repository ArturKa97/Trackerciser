package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;

import java.util.List;

public interface WorkoutSessionService {
    WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO);

    WorkoutSessionDTO getWorkoutSessionById(Long id);

    List<WorkoutSessionDTO> getAllWorkoutSessions();

    void deleteWorkoutSessionById(Long id);

    WorkoutSession updateWorkoutSessionById(Long workoutSessionId, WorkoutSession updatedWorkoutSession);
}
