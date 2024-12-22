package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.services.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public void addExercise(@RequestBody @Valid Exercise exercise) {
        exerciseService.addExercise(exercise);
    }

    @GetMapping("/{id}")
    public ExerciseDTO getExerciseById(@PathVariable(value = "id") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @DeleteMapping("/{id}")
    public void deleteExerciseById(@PathVariable(value = "id") Long id) {
        exerciseService.deleteExerciseById(id);
    }

    @PostMapping("/{workoutSessionId}/{exerciseTypeId}")
    public ExerciseDTO addExerciseToWorkoutSession(@PathVariable(value = "workoutSessionId") Long workoutSessionId, @PathVariable(value = "exerciseTypeId") Long exerciseTypeId) {
        return exerciseService.addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId);
    }

    @DeleteMapping("/{workoutSessionId}/{exerciseId}")
    public void removeExerciseFromWorkoutSession(@PathVariable(value = "workoutSessionId") Long workoutSessionId, @PathVariable(value = "exerciseId") Long exerciseId) {
        exerciseService.removeExerciseFromWorkoutSession(workoutSessionId, exerciseId);
    }

}
