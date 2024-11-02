package com.exercise.project.controllers;

import com.exercise.project.entities.ExerciseSet;
import com.exercise.project.services.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exerciseSet")
@RequiredArgsConstructor
public class ExerciseSetController {
    private final ExerciseSetService exerciseSetService;

    @PostMapping("/{exerciseId}")
    public ExerciseSet addExerciseSetToExercise(@RequestBody ExerciseSet exerciseSet, @PathVariable(value = "exerciseId") Long exerciseId) {
        return exerciseSetService.addExerciseSetToExercise(exerciseSet, exerciseId);
    }

    @DeleteMapping("/{exerciseSetId}")
    public void removeExerciseSetById(@PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        exerciseSetService.removeExerciseSetById(exerciseSetId);
    }

    @PutMapping("/{exerciseSetId}")
    public ExerciseSet updateExerciseSetById(@RequestBody ExerciseSet updatedExerciseSet, @PathVariable(value = "exerciseSetId") Long exerciseSetId) {
        return exerciseSetService.updateExerciseSetById(updatedExerciseSet, exerciseSetId);
    }
}
