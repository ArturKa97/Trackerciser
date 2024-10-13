package com.exercise.project.controllers;

import com.exercise.project.entities.ExerciseType;
import com.exercise.project.services.ExerciseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise_type")
public class ExerciseTypeController {
    private final ExerciseTypeService exerciseTypeService;

    @GetMapping
    public List<ExerciseType> getAllExerciseTypes() {
        return exerciseTypeService.getAllExerciseTypes();

    }
}
