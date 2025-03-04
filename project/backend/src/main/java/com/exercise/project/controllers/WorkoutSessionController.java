package com.exercise.project.controllers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.services.WorkoutSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/workout_session")
@RequiredArgsConstructor
@Tag(name = " User's Workout Session Management")
public class WorkoutSessionController {

    private final WorkoutSessionService workoutSessionService;

    @Operation(description = "Post endpoint for adding a Workout Session to a User",
            summary = "Create and add a new workout session to a user")
    @PostMapping("/{userId}")
    public WorkoutSessionDTO addWorkoutSession(@RequestBody WorkoutSessionDTO workoutSessionDTO, @PathVariable(value = "userId") Long userId) {
        return workoutSessionService.addWorkoutSession(workoutSessionDTO, userId);
    }

    @Operation(description = "Get endpoint for getting a single User's Workout Session",
    summary = "Access a single workout session from user's workout sessions list")
    @GetMapping("/{id}/{userId}")
    public WorkoutSessionDTO getWorkoutSessionById(@PathVariable(value = "id") Long workoutSessionId, @PathVariable(value = "userId") Long userId) {
        return workoutSessionService.getWorkoutSessionById(workoutSessionId, userId);
    }

    @Operation(description = "Get endpoint for retrieving all Workout Sessions belonging to User",
    summary = "Retrieve a list of all of user's workout sessions")
    @GetMapping("/{userId}")
    public Page<WorkoutSessionDTO> getAllWorkoutSessions(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size, @PathVariable(value = "userId") Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        return workoutSessionService.getAllWorkoutSessions(pageable, userId);
    }

    @Operation(description = "Get endpoint for retrieving all Workout Sessions in certain time period, belonging to User",
    summary = "Retrieve all of user's workout sessions in specified time period")
    @GetMapping("/dates/{userId}")
    public List<WorkoutSessionDTO> getAllWorkoutSessionsBetweenDates(@RequestParam String fromDate, @RequestParam String toDate, @PathVariable(value = "userId") Long userId) {
        LocalDate parsedFromDate = LocalDate.parse(fromDate);
        LocalDate parsedToDate = LocalDate.parse(toDate);
        return workoutSessionService.getAllWorkoutSessionsBetweenDates(parsedFromDate, parsedToDate, userId);
    }

    @Operation(description = "Delete endpoint for deleting a single Workout Session, belonging to User",
    summary = "Remove a single workout session from the user's workout sessions list")
    @DeleteMapping("/{id}/{userId}")
    public void deleteWorkoutSessionById(@PathVariable(value = "id") Long workoutSessionId, @PathVariable(value = "userId") Long userId) {
        workoutSessionService.deleteWorkoutSessionById(workoutSessionId, userId);
    }

    @Operation(description = "Put endpoint for editing a single Workout Session, belonging to User",
    summary = "Edit a single workout session from the user's workout sessions list")
    @PutMapping("/{id}/{userId}")
    public WorkoutSessionDTO updateWorkoutSessionById(@PathVariable(value = "id") Long workoutSessionId, @PathVariable(value = "userId") Long userId, @RequestBody WorkoutSessionDTO updatedWorkoutSession) {
        return workoutSessionService.updateWorkoutSessionById(workoutSessionId, userId, updatedWorkoutSession);
    }

}
