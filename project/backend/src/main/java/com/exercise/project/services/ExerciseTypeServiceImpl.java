package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseTypeDTO;
import com.exercise.project.mappers.ExerciseTypeDTOMapper;
import com.exercise.project.repositories.ExerciseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseTypeServiceImpl implements ExerciseTypeService {

    private final ExerciseTypeRepository exerciseTypeRepository;
    private final ExerciseTypeDTOMapper exerciseTypeDTOMapper;

    @Override
    public List<ExerciseTypeDTO> getAllExerciseTypes() {
        return exerciseTypeRepository.getAllExerciseTypes()
                .stream()
                .map(exerciseTypeDTOMapper::toDTO)
                .collect(Collectors.toList());
    }
}
