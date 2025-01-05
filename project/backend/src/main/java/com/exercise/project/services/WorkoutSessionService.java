package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutSessionService {

    WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO);

    WorkoutSessionDTO getWorkoutSessionById(Long id);

    List<WorkoutSessionDTO> getAllWorkoutSessions();

    List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(LocalDate parsedFromDate, LocalDate parsedToDate);

    void deleteWorkoutSessionById(Long id);

    WorkoutSessionDTO updateWorkoutSessionById(Long workoutSessionId, WorkoutSessionDTO updatedWorkoutSession);
}
