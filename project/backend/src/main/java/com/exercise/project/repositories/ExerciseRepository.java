package com.exercise.project.repositories;

import com.exercise.project.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query(value = "SELECT e FROM Exercise e WHERE e.id = :id")
    Exercise getExerciseById(@Param("id") Long id);
}
