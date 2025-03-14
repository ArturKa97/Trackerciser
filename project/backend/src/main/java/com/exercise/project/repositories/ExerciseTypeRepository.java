package com.exercise.project.repositories;

import com.exercise.project.entities.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {

    @Query(value = "SELECT et FROM ExerciseType et WHERE et.id = :id")
    Optional<ExerciseType> getExerciseTypeById(@Param("id") Long id);

    @Query(value = "SELECT et FROM ExerciseType et ")
    List<ExerciseType> getAllExerciseTypes();
}
