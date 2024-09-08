package com.exercise.project.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "exercise")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SetsReps> exerciseInfo = new ArrayList<>();

    public void addExerciseInfo(SetsReps setsReps) {
        exerciseInfo.add(setsReps);
        setsReps.setExercise(this);
    }

    public void removeExerciseInfo(SetsReps setsReps) {
        exerciseInfo.remove(setsReps);
        setsReps.setExercise(null);
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkoutSession workoutSession;
}
