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

    @Query(value = "SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercisesSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets WHERE ws.id = :id AND ws.user.id = :userId")
    Optional<WorkoutSession> getWorkoutSessionById(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "SELECT DISTINCT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercisesSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets WHERE ws.user.id = :userId")
    Page<WorkoutSession> getAllWorkoutSessions(Pageable pageable, @Param("userId") Long userId);

    @Query(value = "SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exercisesSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseSets  WHERE ws.date BETWEEN :fromDate AND :toDate AND ws.user.id = :userId")
    List<WorkoutSession> getWorkoutSessionsBetweenDates(@Param("fromDate") LocalDate fromDate,
                                                        @Param("toDate") LocalDate toDate, @Param("userId") Long userId);

}
