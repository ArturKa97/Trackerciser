package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotNull(message = "Cannot be null")
    private Long sets;

    @Column(name = "reps")
    private Long reps;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "rest")
    private Long rest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Exercise exercise;

}
