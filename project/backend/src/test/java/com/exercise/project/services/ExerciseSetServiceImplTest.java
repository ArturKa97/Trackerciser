package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseSet;
import com.exercise.project.mappers.ExerciseSetDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseSetRepository;
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
class ExerciseSetServiceImplTest {

    @Mock
    private ExerciseSetDTOMapper exerciseSetDTOMapper;
    @Mock
    private ExerciseSetRepository exerciseSetRepository;
    @Mock
    private ExerciseRepository exerciseRepository;
    @InjectMocks
    private ExerciseSetServiceImpl exerciseSetServiceImpl;

    @Test
    public void ExerciseSetServiceImpl_AddExerciseSetToExercise_ShouldReturnExerciseSetDTO() {
        //Given
        Exercise exercise = new Exercise();
        ExerciseSetDTO exerciseSetDTO = ExerciseSetDTO.builder()
                .sets(1L)
                .reps(10L)
                .weight(100F)
                .rest(90L)
                .build();
        ExerciseSet exerciseSet = ExerciseSet.builder()
                .sets(1L)
                .reps(10L)
                .weight(100F)
                .rest(90L)
                .build();

        when(exerciseRepository.getExerciseById(exercise.getId())).
                thenReturn(Optional.of(exercise));
        when(exerciseSetDTOMapper.toEntity(exerciseSetDTO)).thenReturn(exerciseSet);
        when(exerciseSetRepository.save(exerciseSet)).thenReturn(exerciseSet);
        when(exerciseSetDTOMapper.toDTO(exerciseSet)).thenReturn(exerciseSetDTO);
        // When
        ExerciseSetDTO result = exerciseSetServiceImpl.addExerciseSetToExercise(exerciseSetDTO, exercise.getId());
        //Then
        assertNotNull(result);
        assertEquals(exerciseSetDTO, result);
        verify(exerciseRepository, times(1)).getExerciseById(exercise.getId());
        verify(exerciseSetDTOMapper, times(1)).toEntity(exerciseSetDTO);
        verify(exerciseSetRepository, times(1)).save(exerciseSet);
        verify(exerciseSetDTOMapper, times(1)).toDTO(exerciseSet);

    }
    @Test
    public void ExerciseSetServiceImpl_AddExerciseSetToExercise_ShouldThrowEntityNotFoundException() {
        //Given
        Long exerciseId = 99999L;
        ExerciseSetDTO exerciseSetDTO = ExerciseSetDTO.builder()
                .sets(1L)
                .reps(10L)
                .weight(100F)
                .rest(90L)
                .build();
        when(exerciseRepository.getExerciseById(exerciseId)).
                thenReturn(Optional.empty());

        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseSetServiceImpl.addExerciseSetToExercise(exerciseSetDTO, exerciseId);
        });
        assertEquals("Exercise with id [99999] not found", exception.getMessage());
    }
}

