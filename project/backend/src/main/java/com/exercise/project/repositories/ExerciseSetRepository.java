package com.exercise.project.repositories;

import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
    @Query(value = "SELECT es FROM ExerciseSet es WHERE es.id = :id")
    Optional<ExerciseSet> getExerciseSetById(@Param("id") Long id);
}
