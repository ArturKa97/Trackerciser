package com.exercise.project.dtos;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

public record WorkoutSessionDTO(
        Long id,
        String workoutSessionName,

        @DateTimeFormat(pattern = "yyyy/MM/dd")
        @Temporal(TemporalType.DATE)
        Date date,

        Set<ExerciseDTO> exercisesDTO
) {

}
