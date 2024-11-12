package com.exercise.project.dtos;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Builder
public record WorkoutSessionDTO(
        Long id,
        String workoutSessionName,

        @DateTimeFormat(pattern = "yyyy/MM/dd")
        @Temporal(TemporalType.DATE)
        Date date,

        Set<ExerciseDTO> exercisesDTO
) {

}
