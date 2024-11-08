package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseSet;
import com.exercise.project.mappers.ExerciseSetDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseSetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ExerciseSet updateExerciseSetById(ExerciseSet updatedExerciseSet, Long exerciseSetId) {
        Optional<ExerciseSet> optionalExerciseSet = exerciseSetRepository.findById(exerciseSetId);
        if (optionalExerciseSet.isPresent()) {
            ExerciseSet existingExerciseSet = optionalExerciseSet.get();
            existingExerciseSet.setSets(updatedExerciseSet.getSets());
            existingExerciseSet.setReps(updatedExerciseSet.getReps());
            existingExerciseSet.setWeight(updatedExerciseSet.getWeight());
            existingExerciseSet.setRest(updatedExerciseSet.getRest());
            return exerciseSetRepository.save(existingExerciseSet);
        } else {
            throw new RuntimeException("SetsReps with ID " + exerciseSetId + " not found.");
        }
    }
}
