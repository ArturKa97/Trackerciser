package com.exercise.project.repositories;

import com.exercise.project.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "SELECT e FROM Exercise e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets WHERE e.id = :id")
    Optional<Exercise> getExerciseById(@Param("id") Long id);

    @Query(value = "SELECT e FROM Exercise e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets")
    List<Exercise> getAllExercises();
}
