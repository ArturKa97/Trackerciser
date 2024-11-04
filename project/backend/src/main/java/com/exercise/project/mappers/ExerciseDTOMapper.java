package com.exercise.project.mappers;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExerciseSetDTOMapper.class})
public interface ExerciseDTOMapper {

    ExerciseDTO toDTO(Exercise exercise);
}
