package com.exercise.project.mappers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExerciseDTOMapper.class})
public interface WorkoutSessionDTOMapper {
    @Mapping(target = "exerciseSetDTO", source = "workoutSession.exerciseSet")
    WorkoutSessionDTO toDTO(WorkoutSession workoutSession);

}
