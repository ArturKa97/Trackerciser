package com.exercise.project.controllers;

import com.exercise.project.entities.SetsReps;
import com.exercise.project.services.SetsRepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setsreps")
@RequiredArgsConstructor
public class SetsRepsController {
    private final SetsRepsService setsRepsService;

    @PostMapping
    public void addSetsReps (@RequestBody SetsReps setsReps) {
        setsRepsService.addSetsReps(setsReps);
    }
}
