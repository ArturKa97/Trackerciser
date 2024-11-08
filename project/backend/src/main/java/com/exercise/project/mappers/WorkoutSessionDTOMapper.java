package com.exercise.project.mappers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExerciseDTOMapper.class})
public interface WorkoutSessionDTOMapper {

    @Mapping(target = "exercisesDTO", source = "workoutSession.exercisesSet")
    WorkoutSessionDTO toDTO(WorkoutSession workoutSession);

    @Mapping(target = "exercisesSet", source = "workoutSessionDTO.exercisesDTO")
    WorkoutSession toEntity(WorkoutSessionDTO workoutSessionDTO);

}
