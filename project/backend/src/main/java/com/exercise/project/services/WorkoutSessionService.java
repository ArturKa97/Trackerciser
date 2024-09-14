package com.exercise.project.services;
import com.exercise.project.entities.WorkoutSession;
import java.util.List;

public interface WorkoutSessionService {
    void addWorkoutSession(WorkoutSession workoutSession);
    WorkoutSession getWorkoutSessionById(Long id);
    List<WorkoutSession> getAllWorkoutSessions();
}
