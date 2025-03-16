package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "exercise_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ExerciseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull(message = "Exercise type name value cannot be null")
    @NotBlank(message = "Exercise type name value cannot be empty")
    @Size(min = 1, max = 50, message = "Exercise type name value must be between {min} and {max} characters long")
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "exerciseType",  fetch = FetchType.LAZY)
    private Set<Exercise> exercises = new HashSet<>();

}
