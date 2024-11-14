package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.services.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exerciseSet")
@RequiredArgsConstructor
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    @PostMapping("/{exerciseId}")
    public ExerciseSetDTO addExerciseSetToExercise(@RequestBody ExerciseSetDTO exerciseSetDTO, @PathVariable(value = "exerciseId") Long exerciseId) {
        return exerciseSetService.addExerciseSetToExercise(exerciseSetDTO, exerciseId);
    }

    @DeleteMapping("/{exerciseSetId}")
    public void removeExerciseSetById(@PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        exerciseSetService.removeExerciseSetById(exerciseSetId);
    }

    @PutMapping("/{exerciseSetId}")
    public ExerciseSetDTO updateExerciseSetById(@RequestBody ExerciseSetDTO updatedExerciseSet, @PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        return exerciseSetService.updateExerciseSetById(updatedExerciseSet, exerciseSetId);
    }
}
