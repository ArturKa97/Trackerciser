package com.exercise.project.controllers;

import com.exercise.project.entities.Exercise;
import com.exercise.project.services.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
