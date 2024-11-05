package com.exercise.project.dtos;

import java.util.List;

public record ExerciseDTO(
        Long id,
        ExerciseTypeDTO exerciseTypeDTO,
        List<ExerciseSetDTO> exerciseSetsDTO
) {

}
