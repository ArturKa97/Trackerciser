package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "exercise_type")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExerciseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "exerciseType",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Exercise> exercises = new HashSet<>();

    public void addExercises(Exercise exercise) {
        exercises.add(exercise);
        exercise.setExerciseType(this);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkoutSession workoutSession;
}
