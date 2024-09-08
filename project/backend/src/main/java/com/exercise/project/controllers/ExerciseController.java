package com.exercise.project.controllers;

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
    public Exercise getExerciseById(@PathVariable(value = "id") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping
    public List<Exercise> getAllExercises () {
        return exerciseService.getAllExercises();
    }

    @DeleteMapping("/{id}")
    public void deleteExerciseById (@PathVariable(value = "id") Long id) {
        exerciseService.deleteExerciseById(id);
    }

    @PostMapping("/{exerciseId}/{workoutSessionId}")
    public void addExerciseToSession (@PathVariable(value = "exerciseId") Long exerciseId, @PathVariable(value = "workoutSessionId") Long workoutSessionId) {
        exerciseService.addExerciseToSession(exerciseId, workoutSessionId);
    }

}
