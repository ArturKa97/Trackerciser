package com.exercise.project.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ExerciseSetDTO(
        Long id,
        Long sets,
        Long reps,
        BigDecimal weight,
        Long rest) {

}
