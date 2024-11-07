package com.exercise.project.mappers;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExerciseSetDTOMapper.class, ExerciseTypeDTOMapper.class})
public interface ExerciseDTOMapper {

    @Mapping(target = "exerciseTypeDTO", source = "exercise.exerciseType")
    @Mapping(target = "exerciseSetsDTO", source = "exercise.exerciseSets")
    ExerciseDTO toDTO(Exercise exercise);

}
