package com.exercise.project.services;

import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.repositories.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;

    @Override
    public void addWorkoutSession(WorkoutSession workoutSession) {
        workoutSessionRepository.save(workoutSession);
    }
}
