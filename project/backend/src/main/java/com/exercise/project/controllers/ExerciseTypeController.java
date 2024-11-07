package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseTypeDTO;
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
    public List<ExerciseTypeDTO> getAllExerciseTypes() {
        return exerciseTypeService.getAllExerciseTypes();

    }
}
