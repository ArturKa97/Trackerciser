package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.mappers.ExerciseDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceImplTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private ExerciseDTOMapper exerciseDTOMapper;

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
}



