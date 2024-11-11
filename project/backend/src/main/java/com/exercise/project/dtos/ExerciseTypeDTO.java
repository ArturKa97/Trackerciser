package com.exercise.project.dtos;

import lombok.Builder;

@Builder
public record ExerciseTypeDTO(
        Long id,
        String name
) {

}
