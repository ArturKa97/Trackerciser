package com.exercise.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Data
@Entity
@Table(name = "workout_session")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "workoutSession",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ExerciseType> exerciseTypeSet = new HashSet<>();

    public void addExerciseType(ExerciseType exerciseType) {
        exerciseTypeSet.add(exerciseType);
        exerciseType.setWorkoutSession(this);
    }

    public void removeExerciseType(ExerciseType exerciseType) {
        exerciseTypeSet.remove(exerciseType);
        exerciseType.setWorkoutSession(null);
    }
}
