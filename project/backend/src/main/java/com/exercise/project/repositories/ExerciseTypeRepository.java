package com.exercise.project.repositories;
import com.exercise.project.entities.ExerciseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseTypeRepository extends JpaRepository<ExerciseType, Long> {
    @Query(value = "SELECT e FROM ExerciseType e WHERE e.id = :id")
    ExerciseType getExercisTypeById(@Param("id") Long id);

    @Query(value = "SELECT e FROM Exercise e ")
    List<ExerciseType> getAllExerciseTypes();
}
