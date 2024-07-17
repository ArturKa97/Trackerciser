package com.exercise.project.services;

import com.exercise.project.entities.Exercise;
import com.exercise.project.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{
    private final ExerciseRepository exerciseRepository;

    @Override
    public void addExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.getExerciseById(id);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.getAllExercises();
    }

    @Override
    public void deleteExerciseById(Long id) {
        exerciseRepository.deleteById(id);
    }
}
