package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;

import java.util.Date;
import java.util.List;

public interface WorkoutSessionService {

    WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO);

    WorkoutSessionDTO getWorkoutSessionById(Long id);

    List<WorkoutSessionDTO> getAllWorkoutSessions();

    List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(Date parsedFromDate, Date parsedToDate);

    void deleteWorkoutSessionById(Long id);

    WorkoutSessionDTO updateWorkoutSessionById(Long workoutSessionId, WorkoutSessionDTO updatedWorkoutSession);
}
