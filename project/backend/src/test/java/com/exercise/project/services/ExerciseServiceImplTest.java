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

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        assertThat(result)
                .isNotNull()
                .isEqualTo(exerciseDTO);
        verify(exerciseRepository).getExerciseById(exercise.getId());
        verify(exerciseDTOMapper).toDTO(exercise);
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
    public void ExerciseServiceImpl_GetAllExercises_ShouldReturnAnExerciseDTOList() {
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
        assertThat(result)
                .isNotNull()
                .isEqualTo(exercisesDTOs);
        verify(exerciseRepository).getAllExercises();
        verify(exerciseDTOMapper).toDTO(exercise1);
        verify(exerciseDTOMapper).toDTO(exercise2);
    }

    @Test
    public void ExerciseServiceImpl_GetAllExercises_ShouldReturnAnEmptyList() {
        //Given
        when(exerciseServiceImpl.getAllExercises()).thenReturn(Collections.emptyList());
        //When
        List<ExerciseDTO> result = exerciseServiceImpl.getAllExercises();
        //Then
        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(exerciseRepository).getAllExercises();
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldReturnAnExerciseDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
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
        assertThat(result)
                .isNotNull()
                .isEqualTo(exerciseDTO);
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSession.getId());
        verify(exerciseTypeRepository).getExerciseTypeById(exerciseType.getId());
        verify(exerciseRepository).save(exercise);
        verify(exerciseDTOMapper).toDTO(exercise);
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldThrowEntityNotFoundExceptionWhenWorkoutSessionDoesNotExist() {
        //Given
        Long nonExistentWorkoutSessionId = 999999L;
        when(workoutSessionRepository.getWorkoutSessionById(nonExistentWorkoutSessionId)).thenReturn(Optional.empty());
        //When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.addExerciseToWorkoutSession(nonExistentWorkoutSessionId, 1L);
        });
        assertEquals("WorkoutSession with id [999999] not found", exception.getMessage());
        verify(workoutSessionRepository).getWorkoutSessionById(nonExistentWorkoutSessionId);
        verifyNoInteractions(exerciseTypeRepository, exerciseRepository, exerciseDTOMapper);
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldThrowEntityNotFoundExceptionWhenExerciseTypeDoesNotExist() {
        //Given
        Long nonExistentExerciseTypeId = 999999L;
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .exercisesSet(new HashSet<>())
                .build();
        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId())).thenReturn(Optional.of(workoutSession));
        when(exerciseTypeRepository.getExerciseTypeById(nonExistentExerciseTypeId)).thenReturn(Optional.empty());
        //When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.addExerciseToWorkoutSession(1L, nonExistentExerciseTypeId);
        });
        assertEquals("ExerciseType with id [999999] not found", exception.getMessage());
        verify(exerciseTypeRepository).getExerciseTypeById(nonExistentExerciseTypeId);
        verifyNoInteractions(exerciseRepository, exerciseDTOMapper);
    }
}



