package com.exercise.project.services;


import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.SetsReps;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.SetsRepsRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetsRepsServiceImpl implements SetsRepsService{
    private final SetsRepsRepository setsRepsRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutSessionRepository workoutSessionRepository;

    @Override
    public void addInfoToExercise(SetsReps setsReps, Long workoutSessionId, Long exerciseId) {
        WorkoutSession workoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSessionId);
        Exercise exercise = exerciseRepository.getExerciseById(exerciseId);
        workoutSession.addSetsReps(setsReps);
        exercise.addExerciseInfo(setsReps);
        setsRepsRepository.save(setsReps);
    }
}
