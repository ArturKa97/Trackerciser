package com.exercise.project.repositories;

import com.exercise.project.entities.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    @Query(value = "SELECT ws FROM WorkoutSession ws LEFT JOIN FETCH ws.exerciseSet e LEFT JOIN FETCH e.exerciseType LEFT JOIN FETCH e.exerciseInfo WHERE ws.id = :id")
    WorkoutSession getWorkoutSessionById(@Param("id") Long id);

    @Query(value = "SELECT ws FROM WorkoutSession ws")
    List<WorkoutSession> getAllWorkoutSessions();
}
