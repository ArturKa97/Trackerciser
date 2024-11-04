package com.exercise.project.mappers;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.entities.ExerciseSet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseSetDTOMapper {

    ExerciseSetDTO toDTO(ExerciseSet exerciseSet);
}
