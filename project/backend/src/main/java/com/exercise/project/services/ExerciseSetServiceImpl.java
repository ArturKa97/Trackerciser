package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.entities.ExerciseSet;
import com.exercise.project.mappers.ExerciseSetDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseSetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseSetServiceImpl implements ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseSetDTOMapper exerciseSetDTOMapper;

    @Override
    public ExerciseSetDTO addExerciseSetToExercise(ExerciseSetDTO exerciseSetDTO, Long exerciseId) {
        return exerciseRepository.getExerciseById(exerciseId)
                .map(exercise -> {
                    ExerciseSet exerciseSetEntity = exerciseSetDTOMapper.toEntity(exerciseSetDTO);
                    exercise.addExerciseSet(exerciseSetEntity);
                    ExerciseSet savedExerciseSetEntity = exerciseSetRepository.save(exerciseSetEntity);
                    return exerciseSetDTOMapper.toDTO(savedExerciseSetEntity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id [%s] not found".formatted(exerciseId)));
    }

    @Override
    public void removeExerciseSetById(Long exerciseSetId) {
        exerciseSetRepository.deleteById(exerciseSetId);
    }

    @Override
    public ExerciseSetDTO updateExerciseSetById(ExerciseSetDTO updatedExerciseSet, Long exerciseSetId) {
        return exerciseSetRepository.getExerciseSetById(exerciseSetId)
                .map(existingExerciseSet -> {
                    existingExerciseSet.setSets(updatedExerciseSet.sets());
                    existingExerciseSet.setReps(updatedExerciseSet.reps());
                    existingExerciseSet.setWeight(updatedExerciseSet.weight());
                    existingExerciseSet.setRest(updatedExerciseSet.rest());
                    ExerciseSet savedExerciseSet = exerciseSetRepository.save(existingExerciseSet);
                    return exerciseSetDTOMapper.toDTO(savedExerciseSet);
                })
                .orElseThrow(() -> new EntityNotFoundException("Exercise with id [%s] not found".formatted(exerciseSetId)));
    }
}
