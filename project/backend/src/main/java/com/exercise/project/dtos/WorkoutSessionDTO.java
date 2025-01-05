package com.exercise.project.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record WorkoutSessionDTO(
        Long id,
        String workoutSessionName,
        LocalDate date,
        Set<ExerciseDTO> exercisesDTO
) {

}
