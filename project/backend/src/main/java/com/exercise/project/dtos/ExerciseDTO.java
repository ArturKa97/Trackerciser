package com.exercise.project.dtos;

import com.exercise.project.entities.ExerciseType;

import java.util.List;

public record ExerciseDTO(
        Long id,
        ExerciseType exerciseType,
        List<ExerciseSetDTO> exerciseSets
) {

}
