package com.exercise.project.services;


import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.SetsReps;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.SetsRepsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SetsRepsServiceImpl implements SetsRepsService {
    private final SetsRepsRepository setsRepsRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public SetsReps addInfoToExercise(SetsReps setsReps, Long exerciseId) {
        Exercise exercise = exerciseRepository.getExerciseById(exerciseId);
        exercise.addExerciseInfo(setsReps);
        return setsRepsRepository.save(setsReps);
    }

    @Override
    public void removeInfoById(Long setsRepsId) {
        setsRepsRepository.deleteById(setsRepsId);
    }

    @Override
    public SetsReps updateExerciseInfoById(SetsReps updatedSetsReps, Long setsRepsId) {
        Optional<SetsReps> optionalSetsReps = setsRepsRepository.findById(setsRepsId);
        if (optionalSetsReps.isPresent()) {
            SetsReps existingSetsReps = optionalSetsReps.get();
            existingSetsReps.setSets(updatedSetsReps.getSets());
            existingSetsReps.setReps(updatedSetsReps.getReps());
            existingSetsReps.setWeight(updatedSetsReps.getWeight());
            existingSetsReps.setRest(updatedSetsReps.getRest());
            return setsRepsRepository.save(existingSetsReps);
        } else {
            throw new RuntimeException("SetsReps with ID " + setsRepsId + " not found.");
        }
    }
}
