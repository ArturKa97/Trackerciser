package com.exercise.project.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "exercise")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WorkoutSession workoutSession;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExerciseType exerciseType;

    @OneToMany(mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ExerciseSet> exerciseSets = new ArrayList<>();

    public void addExerciseSet(ExerciseSet exerciseSet) {
        exerciseSets.add(exerciseSet);
        exerciseSet.setExercise(this);
    }

    public void removeExerciseSet(ExerciseSet exerciseSet) {
        exerciseSets.remove(exerciseSet);
        exerciseSet.setExercise(null);
    }

}
