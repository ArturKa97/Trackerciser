package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutSessionService {

    WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO, Long userId);

    WorkoutSessionDTO getWorkoutSessionById(Long id);

    Page<WorkoutSessionDTO> getAllWorkoutSessions(Pageable pageable);

    List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(LocalDate parsedFromDate, LocalDate parsedToDate);

    void deleteWorkoutSessionById(Long id);

    WorkoutSessionDTO updateWorkoutSessionById(Long workoutSessionId, WorkoutSessionDTO updatedWorkoutSession);
}
