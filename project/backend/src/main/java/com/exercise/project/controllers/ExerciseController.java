package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.services.ExerciseService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
@Tag(name = "User's Exercise Management Inside Of Workout Session")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(description = "Post endpoint for adding a single Exercise",
    summary = "Used only for testing in development")
    @PostMapping
    public void addExercise(@RequestBody @Valid Exercise exercise) {
        exerciseService.addExercise(exercise);
    }

    @Operation(description = "Get endpoint for retrieving a single Exercise",
    summary = "Used for internal operations where the fetching of exercise is necessary, and testing in development")
    @GetMapping("/{id}")
    public ExerciseDTO getExerciseById(@PathVariable(value = "id") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @Operation(description = "Get endpoint for retrieving all Exercises",
    summary = "Used only for testing in development")
    @GetMapping
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @Operation(description = "Delete endpoint for deleting a single Exercise",
    summary = "Used only for testing in development")
    @DeleteMapping("/{id}")
    public void deleteExerciseById(@PathVariable(value = "id") Long id) {
        exerciseService.deleteExerciseById(id);
    }

    @Operation(description = "Post endpoint for adding a single Exercise to the User's Workout Session",
    summary = "Create and add a single exercise to the user's workout session")
    @PostMapping("/{workoutSessionId}/{exerciseTypeId}/{userId}")
    public ExerciseDTO addExerciseToWorkoutSession(@PathVariable(value = "workoutSessionId") Long workoutSessionId, @PathVariable(value = "exerciseTypeId") Long exerciseTypeId,
                                                   @PathVariable(value = "userId") Long userId) {
        return exerciseService.addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId, userId);
    }

    @Operation(description = "Delete endpoint for deleting a single Exercise from the User's Workout Session",
    summary = "Delete a single exercise form the user's workout session")
    @DeleteMapping("/{workoutSessionId}/{exerciseId}/{userId}")
    public void removeExerciseFromWorkoutSession(@PathVariable(value = "workoutSessionId") Long workoutSessionId, @PathVariable(value = "exerciseId") Long exerciseId,
                                                 @PathVariable(value = "userId") Long userId) {
        exerciseService.removeExerciseFromWorkoutSession(workoutSessionId, exerciseId, userId);
    }

}
