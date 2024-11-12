package com.exercise.project.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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

    @Column(name = "workoutSessionName")
    private String workoutSessionName;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "workoutSession",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Exercise> exercisesSet = new HashSet<>();

    public void addExercise(Exercise exercise) {
        exercisesSet.add(exercise);
        exercise.setWorkoutSession(this);
    }

    public void removeExercise(Exercise exercise) {
        exercisesSet.remove(exercise);
        exercise.setWorkoutSession(null);
    }
}
