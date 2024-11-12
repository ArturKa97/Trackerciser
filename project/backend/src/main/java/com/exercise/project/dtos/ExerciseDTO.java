package com.exercise.project.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record ExerciseDTO(
        Long id,
        ExerciseTypeDTO exerciseTypeDTO,
        List<ExerciseSetDTO> exerciseSetsDTO
) {

}
