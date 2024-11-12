package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.WorkoutSessionDTOMapper;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutSessionDTOMapper workoutSessionDTOMapper;

    @Override
    public WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO) {
        WorkoutSession workoutSessionEntity = workoutSessionDTOMapper.toEntity(workoutSessionDTO);
        WorkoutSession savedWorkoutSessionEntity = workoutSessionRepository.save(workoutSessionEntity);
        return workoutSessionDTOMapper.toDTO(savedWorkoutSessionEntity);
    }

    @Override
    public WorkoutSessionDTO getWorkoutSessionById(Long id) {
        return workoutSessionRepository.getWorkoutSessionById(id)
                .map(workoutSessionDTOMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found".formatted(id)));
    }

    @Override
    public List<WorkoutSessionDTO> getAllWorkoutSessions() {
        return workoutSessionRepository.getAllWorkoutSessions()
                .stream()
                .map(workoutSessionDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteWorkoutSessionById(Long id) {
        workoutSessionRepository.deleteById(id);
    }

    @Override
    public WorkoutSessionDTO updateWorkoutSessionById(Long workoutSessionId, WorkoutSessionDTO updatedWorkoutSession) {
        return workoutSessionRepository.getWorkoutSessionById(workoutSessionId)
                .map(workoutSession -> {
                    workoutSession.setWorkoutSessionName(updatedWorkoutSession.workoutSessionName());
                    workoutSession.setDate(updatedWorkoutSession.date());
                    WorkoutSession savedWorkoutSession = workoutSessionRepository.save(workoutSession);
                    return workoutSessionDTOMapper.toDTO(savedWorkoutSession);
                })
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found".formatted(workoutSessionId)));
    }
}
