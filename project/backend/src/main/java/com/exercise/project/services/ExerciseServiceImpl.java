package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseType;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.ExerciseDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseTypeRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDTOMapper exerciseDTOMapper;

    @Override
    public void addExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseDTO getExerciseById(Long id) {
        return exerciseRepository.getExerciseById(id)
                .map(exerciseDTOMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id [%s] not found".formatted(id)));
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.getAllExercises()
                .stream()
                .map(exerciseDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExerciseById(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public ExerciseDTO addExerciseToWorkoutSession(Long workoutSessionId, Long exerciseTypeId, Long userId) {
        WorkoutSession workoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSessionId, userId)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found on User with id [%s]".formatted(workoutSessionId, userId)));

        ExerciseType exerciseType = exerciseTypeRepository.getExerciseTypeById(exerciseTypeId)
                .orElseThrow(() -> new EntityNotFoundException("ExerciseType with id [%s] not found".formatted(exerciseTypeId)));

        Exercise exercise = new Exercise();
        exercise.setExerciseType(exerciseType);
        workoutSession.addExercise(exercise);
        Exercise savedExerciseEntity = exerciseRepository.save(exercise);
        return exerciseDTOMapper.toDTO(savedExerciseEntity);
    }
    @Override
    public void removeExerciseFromWorkoutSession(Long workoutSessionId, Long exerciseId, Long userId) {
        WorkoutSession workoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSessionId, userId)
                .orElseThrow(() -> new EntityNotFoundException("WorkoutSession with id [%s] not found on User with id [%s]".formatted(workoutSessionId, userId)));

        Exercise exercise = exerciseRepository.getExerciseById(exerciseId)
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id [%s] not found".formatted(exerciseId)));

        workoutSession.removeExercise(exercise);
        workoutSessionRepository.save(workoutSession);
    }
}
