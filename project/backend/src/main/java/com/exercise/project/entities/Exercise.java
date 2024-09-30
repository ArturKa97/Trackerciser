package com.exercise.project.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.ALL )
    private ExerciseType exerciseType;

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

}
