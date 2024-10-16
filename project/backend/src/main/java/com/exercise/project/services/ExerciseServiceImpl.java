package com.exercise.project.services;

import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseType;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseTypeRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public void addExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.getExerciseById(id);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.getAllExercises();
    }

    @Override
    public void deleteExerciseById(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public void addExerciseToWorkoutSession(Long workoutSessionId, Long exerciseTypeId) {
        WorkoutSession workoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSessionId);
        ExerciseType exerciseType = exerciseTypeRepository.getExerciseTypeById(exerciseTypeId);
        Exercise exercise = new Exercise();
        exercise.setExerciseType(exerciseType);
        workoutSession.addExercise(exercise);
        exerciseRepository.save(exercise);




    }

}
