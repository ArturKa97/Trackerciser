package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.WorkoutSessionDTOMapper;
import com.exercise.project.repositories.UserRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final WorkoutSessionDTOMapper workoutSessionDTOMapper;
    private final UserRepository userRepository;

    @Override
    public WorkoutSessionDTO addWorkoutSession(WorkoutSessionDTO workoutSessionDTO, Long userId) {
        return userRepository.getUserById(userId)
                .map(user -> {
                    WorkoutSession workoutSessionEntity = workoutSessionDTOMapper.toEntity(workoutSessionDTO);
                    user.addWorkoutSession(workoutSessionEntity);
                    WorkoutSession savedWorkoutSessionEntity = workoutSessionRepository.save(workoutSessionEntity);
                    return workoutSessionDTOMapper.toDTO(savedWorkoutSessionEntity);
                })
                .orElseThrow(() -> new EntityNotFoundException("User with id [%s] not found".formatted(userId)));
    }

    @Override
    public WorkoutSessionDTO getWorkoutSessionById(Long workoutSessionId, Long userId) {
        return workoutSessionRepository.getWorkoutSessionById(workoutSessionId, userId)
                .map(workoutSessionDTOMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found on User with id [%s]".formatted(workoutSessionId, userId)));
    }

    @Override
    public Page<WorkoutSessionDTO> getAllWorkoutSessions(Pageable pageable, Long userId) {
        return workoutSessionRepository.getAllWorkoutSessions(pageable, userId)
                .map(workoutSessionDTOMapper::toDTO);
    }
    @Override
    public List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(LocalDate parsedFromDate, LocalDate parsedToDate, Long userId) {
        return workoutSessionRepository.getWorkoutSessionsBetweenDates(parsedFromDate, parsedToDate, userId)
                .stream()
                .map(workoutSessionDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteWorkoutSessionById(Long workoutSessionId, Long userId) {
        WorkoutSession workoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSessionId, userId)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found on User with id [%s]".formatted(workoutSessionId, userId)));
        workoutSessionRepository.delete(workoutSession);
    }

    @Override
    public WorkoutSessionDTO updateWorkoutSessionById(Long workoutSessionId, Long userId, WorkoutSessionDTO updatedWorkoutSession) {
        return workoutSessionRepository.getWorkoutSessionById(workoutSessionId, userId)
                .map(workoutSession -> {
                    workoutSession.setWorkoutSessionName(updatedWorkoutSession.workoutSessionName());
                    workoutSession.setDate(updatedWorkoutSession.date());
                    WorkoutSession savedWorkoutSession = workoutSessionRepository.save(workoutSession);
                    return workoutSessionDTOMapper.toDTO(savedWorkoutSession);
                })
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found on User with id [%s]".formatted(workoutSessionId, userId)));
    }
}
