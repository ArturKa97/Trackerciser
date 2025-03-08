package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.services.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            summary = "Used only for testing in development, will be hidden")
    @PostMapping
    public void addExercise(@RequestBody @Valid Exercise exercise) {
        exerciseService.addExercise(exercise);
    }

    @Operation(description = "Get endpoint for retrieving a single Exercise",
            summary = "Used only for testing in development, will be hidden")
    @GetMapping("/{id}")
    public ExerciseDTO getExerciseById(@PathVariable(value = "id") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @Operation(description = "Get endpoint for retrieving all Exercises",
            summary = "Used only for testing in development, will be hidden")
    @GetMapping
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @Operation(description = "Delete endpoint for deleting a single Exercise",
            summary = "Used only for testing in development, will be hidden")
    @DeleteMapping("/{id}")
    public void deleteExerciseById(@PathVariable(value = "id") Long id) {
        exerciseService.deleteExerciseById(id);
    }

    @Operation(description = "Post endpoint for adding a single Exercise to the User's Workout Session",
            summary = "Create and add a single exercise to the user's workout session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adds a new exercise to selected workout session belonging to the user and returns it",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExerciseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User with Workout Session or Exercise Type was not found with the provided Id's", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/{workoutSessionId}/{exerciseTypeId}/{userId}")
    public ExerciseDTO addExerciseToWorkoutSession(@Parameter(in = ParameterIn.PATH, description = "Id of the workout session that belongs to user", required = true)
                                                   @PathVariable(value = "workoutSessionId") Long workoutSessionId,
                                                   @Parameter(in = ParameterIn.PATH, description = "Id of the exercise type to add", required = true)
                                                   @PathVariable(value = "exerciseTypeId") Long exerciseTypeId,
                                                   @Parameter(in = ParameterIn.PATH, description = "Id of the user to whom to add the exercise to the workout session")
                                                   @PathVariable(value = "userId") Long userId) {
        return exerciseService.addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId, userId);
    }

    @Operation(description = "Delete endpoint for deleting a single Exercise from the User's Workout Session",
            summary = "Delete a single exercise form the user's workout session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removes the exercise from the user's workout session",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User with Workout Session or Exercise was not found with the provided Id's", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @DeleteMapping("/{workoutSessionId}/{exerciseId}/{userId}")
    public void removeExerciseFromWorkoutSession(@Parameter(in = ParameterIn.PATH, description = "Id of the workout session that belongs to user", required = true)
                                                 @PathVariable(value = "workoutSessionId") Long workoutSessionId,
                                                 @Parameter(in = ParameterIn.PATH, description = "Id of the exercise to delete form the workout session", required = true)
                                                 @PathVariable(value = "exerciseId") Long exerciseId,
                                                 @Parameter(in = ParameterIn.PATH, description = "Id of the user from whom to delete the exercise from the workout session")
                                                 @PathVariable(value = "userId") Long userId) {
        exerciseService.removeExerciseFromWorkoutSession(workoutSessionId, exerciseId, userId);
    }

}
