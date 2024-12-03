package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "exercise_set")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sets")
    @NotNull(message = "Sets value cannot be null")
    @Min(value = 1, message = "Number of sets must be at least {value}")
    @Max(value = 100, message = "Number of sets cannot exceed {value}")
    @Positive(message = "Sets must be a positive number")
    private Long sets;

    @Column(name = "reps")
    @NotNull(message = "Reps value cannot be null")
    @Min(value = 1, message = "Number of reps must be at least {value}")
    @Max(value = 100, message = "Number of reps cannot exceed {value}")
    @Positive(message = "Reps should be a positive number")
    private Long reps;

    @Column(name = "weight")
    @NotNull(message = "Weight value cannot be null")
    @Min(value = 0, message = "Weight should positive or {value} if no weight is used")
    @Max(value = 2000, message = "Weight should not exceed {value}")
    @PositiveOrZero(message = "Weight should be a positive number or 0")
    @Digits(integer = 4, fraction = 2, message = "Weight must have up to {integer} digits before and {fraction} digits after the decimal point.")
    private BigDecimal weight;

    @Column(name = "rest")
    @NotNull(message = "Rest value cannot be null")
    @Min(value = 0, message = "Resting time must be a positive number or {value} for default value")
    @Max(value = 1800, message = "Resting time  cannot exceed {value} seconds")
    @PositiveOrZero(message = "Rest should be a positive number or 0")
    private Long rest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

}
