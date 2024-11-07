package com.exercise.project.controllers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.services.WorkoutSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout_session")
@RequiredArgsConstructor
public class WorkoutSessionController {
    private final WorkoutSessionService workoutSessionService;

    @PostMapping
    public WorkoutSession addWorkoutSession(@RequestBody WorkoutSession workoutSession) {
        return workoutSessionService.addWorkoutSession(workoutSession);
    }

    @GetMapping("/{id}")
    public WorkoutSessionDTO getWorkoutSessionById(@PathVariable(value = "id") Long id) {
        return workoutSessionService.getWorkoutSessionById(id);
    }

    @GetMapping
    public List<WorkoutSessionDTO> getAllWorkoutSessions() {
        return workoutSessionService.getAllWorkoutSessions();
    }

    @DeleteMapping("/{id}")
    public void deleteWorkoutSessionById(@PathVariable(value = "id") Long id) {
        workoutSessionService.deleteWorkoutSessionById(id);
    }

    @PutMapping("/{workoutSessionId}")
    public WorkoutSession updateWorkoutSessionById(@PathVariable(value = "workoutSessionId") Long workoutSessionId, @RequestBody WorkoutSession updatedWorkoutSession) {
        return workoutSessionService.updateWorkoutSessionById(workoutSessionId, updatedWorkoutSession);
    }

}
