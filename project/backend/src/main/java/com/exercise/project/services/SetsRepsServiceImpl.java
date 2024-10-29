package com.exercise.project.services;


import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.SetsReps;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.SetsRepsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
