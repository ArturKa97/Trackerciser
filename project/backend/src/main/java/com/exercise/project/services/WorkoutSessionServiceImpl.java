package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.WorkoutSessionDTOMapper;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutSessionDTOMapper workoutSessionDTOMapper;

    @Override
    public WorkoutSession addWorkoutSession(WorkoutSession workoutSession) {
        return workoutSessionRepository.save(workoutSession);
    }

    @Override
    public WorkoutSessionDTO getWorkoutSessionById(Long id) {
        return workoutSessionRepository.getWorkoutSessionById(id)
                .map(workoutSessionDTOMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Workout session with id [%s] not found".formatted(id)));
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
        return workoutSessionRepository.getWorkoutSessionById(workoutSessionId)
                .map(workoutSession -> {
                    workoutSession.setWorkoutSessionName(updatedWorkoutSession.getWorkoutSessionName());
                    workoutSession.setDate(updatedWorkoutSession.getDate());
                    return workoutSessionRepository.save(workoutSession);
                })
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id [%s] not found".formatted(workoutSessionId)));

    }
}
