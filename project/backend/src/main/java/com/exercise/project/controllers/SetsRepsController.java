package com.exercise.project.controllers;

import com.exercise.project.entities.SetsReps;
import com.exercise.project.services.SetsRepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setsreps")
@RequiredArgsConstructor
public class SetsRepsController {
    private final SetsRepsService setsRepsService;

    @PostMapping("/{exerciseId}")
    public void addInfoToExercise (@RequestBody SetsReps setsReps, @PathVariable(value = "exerciseId") Long exerciseId) {
        setsRepsService.addInfoToExercise(setsReps, exerciseId);
    }
}
