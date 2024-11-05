package com.exercise.project.mappers;

import com.exercise.project.dtos.ExerciseTypeDTO;
import com.exercise.project.entities.ExerciseType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseTypeDTOMapper {
    ExerciseTypeDTO toDTO(ExerciseType exerciseType);
}
