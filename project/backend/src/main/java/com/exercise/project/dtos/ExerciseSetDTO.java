package com.exercise.project.dtos;

public record ExerciseSetDTO(
        Long id,
        Long sets,
        Long reps,
        Float weight,
        Long rest) {

}
