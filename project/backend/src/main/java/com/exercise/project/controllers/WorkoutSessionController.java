package com.exercise.project.controllers;

import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.services.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout_session")
@RequiredArgsConstructor
public class WorkoutSessionController {
    private final WorkoutSessionService workoutSessionService;

    @PostMapping
    public void addWorkoutSession (@RequestBody WorkoutSession workoutSession) {
        workoutSessionService.addWorkoutSession(workoutSession);
    }
}
