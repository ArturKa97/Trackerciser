package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "workout_session")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "workout_session_name")
    @NotNull(message = "Workout session name value cannot be null")
    @NotBlank(message = "Workout session name value cannot be empty")
    @Size(min = 1, max = 50, message = "Workout session name value must be between {min} and {max} characters long")
    private String workoutSessionName;

    @Column(name = "date")
    @NotNull(message = "Date value cannot be null")
    @PastOrPresent(message = "Date value can only be in the past or the present time")
    private LocalDate date;

    @OneToMany(mappedBy = "workoutSession",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Exercise> exercisesSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void addExercise(Exercise exercise) {
        exercisesSet.add(exercise);
        exercise.setWorkoutSession(this);
    }

    public void removeExercise(Exercise exercise) {
        exercisesSet.remove(exercise);
        exercise.setWorkoutSession(null);
    }
}
