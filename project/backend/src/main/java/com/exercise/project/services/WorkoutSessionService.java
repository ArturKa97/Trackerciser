package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutSessionService {

    WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO, Long userId);

    WorkoutSessionDTO getWorkoutSessionById(Long workoutSessionId, Long userId);

    Page<WorkoutSessionDTO> getAllWorkoutSessions(Pageable pageable, Long userId);

    List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(LocalDate parsedFromDate, LocalDate parsedToDate, Long userId);

    void deleteWorkoutSessionById(Long workoutSessionId, Long userId);

    WorkoutSessionDTO updateWorkoutSessionById(Long workoutSessionId, Long userId, WorkoutSessionDTO updatedWorkoutSession);
}
