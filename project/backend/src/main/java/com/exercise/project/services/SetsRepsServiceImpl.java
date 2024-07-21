package com.exercise.project.services;


import com.exercise.project.entities.SetsReps;
import com.exercise.project.repositories.SetsRepsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetsRepsServiceImpl implements SetsRepsService{
    private final SetsRepsRepository setsRepsRepository;

    @Override
    public void addSetsReps(SetsReps setsReps) {
        setsRepsRepository.save(setsReps);
    }
}
