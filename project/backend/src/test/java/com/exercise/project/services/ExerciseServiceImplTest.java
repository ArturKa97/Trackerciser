package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseType;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.ExerciseDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseTypeRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private ExerciseDTOMapper exerciseDTOMapper;

    @Mock
    private WorkoutSessionRepository workoutSessionRepository;

    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;

    @InjectMocks
    private ExerciseServiceImpl exerciseServiceImpl;

    @Test
    public void ExerciseServiceImpl_GetExerciseById_ShouldReturnAnExerciseDTO() {
        //Given
        Exercise exercise = Exercise.builder()
                .id(1L)
                .build();
        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .id(1L)
                .build();

        when(exerciseRepository.getExerciseById(exercise.getId())).thenReturn(Optional.of(exercise));
        when(exerciseDTOMapper.toDTO(exercise)).thenReturn(exerciseDTO);
        //When
        ExerciseDTO result = exerciseServiceImpl.getExerciseById(exercise.getId());
        //Then
        assertNotNull(result);
        assertEquals(result, exerciseDTO);
        verify(exerciseRepository, times(1)).getExerciseById(exercise.getId());
        verify(exerciseDTOMapper, times(1)).toDTO(exercise);
    }

    @Test
    public void ExerciseServiceImpl_GetExerciseById_ShouldThrowEntityNotFoundException() {
        //Given
        Long nonExistentId = 999999L;

        when(exerciseRepository.getExerciseById(nonExistentId)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.getExerciseById(nonExistentId);
        });
        assertEquals("Exercise with id [999999] not found", exception.getMessage());
    }

    @Test
    public void ExerciseServiceImpl_GetAllExercises_ShouldReturnAnExerciseList() {
        //Given
        Exercise exercise1 = Exercise.builder()
                .id(1L)
                .build();

        Exercise exercise2 = Exercise.builder()
                .id(2L)
                .build();

        List<Exercise> exercises = Arrays.asList(exercise1, exercise2);

        ExerciseDTO exerciseDTO1 = ExerciseDTO.builder()
                .id(1L)
                .build();

        ExerciseDTO exerciseDTO2 = ExerciseDTO.builder()
                .id(1L)
                .build();

        List<ExerciseDTO> exercisesDTOs = Arrays.asList(exerciseDTO1, exerciseDTO2);

        when(exerciseRepository.getAllExercises()).thenReturn(exercises);
        when(exerciseDTOMapper.toDTO(exercise1)).thenReturn(exerciseDTO1);
        when(exerciseDTOMapper.toDTO(exercise2)).thenReturn(exerciseDTO2);
        //When
        List<ExerciseDTO> result = exerciseServiceImpl.getAllExercises();
        //Then
        assertNotNull(result);
        assertEquals(result, exercisesDTOs);
        verify(exerciseRepository, times(1)).getAllExercises();
        verify(exerciseDTOMapper, times(1)).toDTO(exercise1);
        verify(exerciseDTOMapper, times(1)).toDTO(exercise2);
    }

    @Test
    public void ExerciseServiceImpl_GetAllExercises_ShouldReturnAnEmptyList() {
        //Given
        when(exerciseServiceImpl.getAllExercises()).thenReturn(Collections.emptyList());
        //When
        List<ExerciseDTO> result = exerciseServiceImpl.getAllExercises();
        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(exerciseRepository, times(1)).getAllExercises();
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldReturnAnExerciseDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(new Date())
                .exercisesSet(new HashSet<>())
                .build();

        ExerciseType exerciseType = ExerciseType.builder()
                .id(1L)
                .name("Squats")
                .build();

        Exercise exercise = new Exercise();

        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .id(1L)
                .build();

        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId())).thenReturn(Optional.of(workoutSession));
        when(exerciseTypeRepository.getExerciseTypeById(exerciseType.getId())).thenReturn(Optional.of(exerciseType));
        when(exerciseRepository.save(exercise)).thenReturn(exercise);
        when(exerciseDTOMapper.toDTO(exercise)).thenReturn(exerciseDTO);

        //When
        ExerciseDTO result = exerciseServiceImpl.addExerciseToWorkoutSession(workoutSession.getId(), exerciseType.getId());
        //Then
        assertNotNull(result);
        assertEquals(result, exerciseDTO);
        verify(workoutSessionRepository, times(1)).getWorkoutSessionById(workoutSession.getId());
        verify(exerciseTypeRepository, times(1)).getExerciseTypeById(exerciseType.getId());
        verify(exerciseRepository, times(1)).save(exercise);
        verify(exerciseDTOMapper, times(1)).toDTO(exercise);
    }

}


