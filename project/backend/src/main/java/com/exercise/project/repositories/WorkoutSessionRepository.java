package com.exercise.project.repositories;

import com.exercise.project.entities.WorkoutSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    @Query(value = "SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercisesSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets WHERE ws.id = :id")
    Optional<WorkoutSession> getWorkoutSessionById(@Param("id") Long id);

    @Query(value = "SELECT DISTINCT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercisesSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets")
    Page<WorkoutSession> getAllWorkoutSessions(Pageable pageable);

    @Query(value = "SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercisesSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets  WHERE ws.date BETWEEN:fromDate AND:toDate")
    List<WorkoutSession> getWorkoutSessionsBetweenDates(@Param("fromDate") LocalDate fromDate,
                                                        @Param("toDate") LocalDate toDate);

}
