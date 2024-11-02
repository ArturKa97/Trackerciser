package com.exercise.project.services;


import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseSet;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseSet addExerciseSetToExercise(ExerciseSet exerciseSet, Long exerciseId) {
        Exercise exercise = exerciseRepository.getExerciseById(exerciseId);
        exercise.addExerciseSet(exerciseSet);
        return exerciseSetRepository.save(exerciseSet);
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
