package com.exercise.project.services;

import com.exercise.project.entities.ExerciseType;
import com.exercise.project.repositories.ExerciseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseTypeServiceImpl implements ExerciseTypeService {
    private final ExerciseTypeRepository exerciseTypeRepository;

    @Override
    public List<ExerciseType> getAllExerciseTypes() {
        return exerciseTypeRepository.getAllExerciseTypes();
    }
}
