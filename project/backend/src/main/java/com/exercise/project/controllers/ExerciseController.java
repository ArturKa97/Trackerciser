package com.exercise.project.controllers;

import com.exercise.project.entities.Exercise;
import com.exercise.project.services.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping
    public void addExercise(@RequestBody @Valid Exercise exercise) {
        exerciseService.addExercise(exercise);
    }
}
