package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.services.ExerciseSetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exerciseSet")
@RequiredArgsConstructor
@Tag(name = "Exercise Set Management For User's Exercises From Workout Sessions")
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    @Operation(description = "Post endpoint for adding the Exercise Set information to the Exercise",
    summary = "Create and add the exercise set information like sets, reps, etc... for selected exercise")
    @PostMapping("/{exerciseId}")
    public ExerciseSetDTO addExerciseSetToExercise(@RequestBody ExerciseSetDTO exerciseSetDTO, @PathVariable(value = "exerciseId") Long exerciseId) {
        return exerciseSetService.addExerciseSetToExercise(exerciseSetDTO, exerciseId);
    }

    @Operation(description = "Delete endpoint for deleting the Exercise Set information from the Exercise",
    summary = "Delete the exercise set information like sets, reps, etc... from the exercise")
    @DeleteMapping("/{exerciseSetId}")
    public void removeExerciseSetById(@PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        exerciseSetService.removeExerciseSetById(exerciseSetId);
    }

    @Operation(description = "Put endpoint for updating the Exercise Set information in the Exercise",
    summary = "Edit the selected exercise set in the exercise with the new values")
    @PutMapping("/{exerciseSetId}")
    public ExerciseSetDTO updateExerciseSetById(@RequestBody ExerciseSetDTO updatedExerciseSet, @PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        return exerciseSetService.updateExerciseSetById(updatedExerciseSet, exerciseSetId);
    }
}
