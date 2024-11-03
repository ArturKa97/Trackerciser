package com.exercise.project.services;

import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.repositories.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;

    @Override
    public WorkoutSession addWorkoutSession(WorkoutSession workoutSession) {
        return workoutSessionRepository.save(workoutSession);
    }

    @Override
    public WorkoutSession getWorkoutSessionById(Long id) {
        return workoutSessionRepository.getWorkoutSessionById(id);
    }

    @Override
    public List<WorkoutSession> getAllWorkoutSessions() {
        return workoutSessionRepository.getAllWorkoutSessions();

    }

    public void deleteWorkoutSessionById(Long id) {
        workoutSessionRepository.deleteById(id);
    }

    @Override
    public WorkoutSession updateWorkoutSessionById(Long workoutSessionId, WorkoutSession updatedWorkoutSession) {
        WorkoutSession initialWorkoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSessionId);
        initialWorkoutSession.setWorkoutSessionName(updatedWorkoutSession.getWorkoutSessionName());
        initialWorkoutSession.setDate(updatedWorkoutSession.getDate());
        return workoutSessionRepository.save(initialWorkoutSession);
    }
}
