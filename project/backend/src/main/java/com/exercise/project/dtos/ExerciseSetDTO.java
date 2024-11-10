package com.exercise.project.dtos;

import lombok.Builder;

@Builder
public record ExerciseSetDTO(
        Long id,
        Long sets,
        Long reps,
        Float weight,
        Long rest) {

}
