package com.exercise.project.repositories;
import com.exercise.project.entities.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {
    @Query(value = "SELECT w FROM WorkoutSession w WHERE w.id = :id")
    WorkoutSession getWorkoutSessionById(@Param("id") Long id);

    @Query(value = "SELECT w FROM WorkoutSession w")
    List<WorkoutSession> getAllWorkoutSessions();
}
