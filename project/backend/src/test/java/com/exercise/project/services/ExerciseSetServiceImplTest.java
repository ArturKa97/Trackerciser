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

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .id(1L)
                .sets(1L)
                .reps(10L)
                .weight(BigDecimal.valueOf(100F))
                .rest(90L)
                .build();
        ExerciseSet exerciseSet = ExerciseSet.builder()
                .id(1L)
                .sets(1L)
                .reps(10L)
                .weight(BigDecimal.valueOf(100F))
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
        assertThat(result)
                .isNotNull()
                .isEqualTo(exerciseSetDTO);
        verify(exerciseRepository).getExerciseById(exercise.getId());
        verify(exerciseSetDTOMapper).toEntity(exerciseSetDTO);
        verify(exerciseSetRepository).save(exerciseSet);
        verify(exerciseSetDTOMapper).toDTO(exerciseSet);

    }
    @Test
    public void ExerciseSetServiceImpl_AddExerciseSetToExercise_ShouldThrowEntityNotFoundException() {
        //Given
        Long exerciseId = 99999L;
        ExerciseSetDTO exerciseSetDTO = ExerciseSetDTO.builder()
                .id(1L)
                .sets(1L)
                .reps(10L)
                .weight(BigDecimal.valueOf(100F))
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

    @Test
    public void ExerciseSetServiceImpl_UpdateExerciseSetById_ShouldReturnUpdatedExerciseSetDTO() {
        //Given
        ExerciseSet existingExerciseSet = ExerciseSet.builder()
                .id(1L)
                .sets(1L)
                .reps(10L)
                .weight(BigDecimal.valueOf(100F))
                .rest(90L)
                .build();

        ExerciseSet updatedExerciseSet = ExerciseSet.builder()
                .id(1L)
                .sets(2L)
                .reps(20L)
                .weight(BigDecimal.valueOf(200F))
                .rest(100L)
                .build();

        ExerciseSetDTO updatedExerciseSetDTO = ExerciseSetDTO.builder()
                .id(1L)
                .sets(2L)
                .reps(20L)
                .weight(BigDecimal.valueOf(200F))
                .rest(100L)
                .build();

        when(exerciseSetRepository.getExerciseSetById(existingExerciseSet.getId())).thenReturn(Optional.of(existingExerciseSet));
        when(exerciseSetRepository.save(existingExerciseSet)).thenReturn(updatedExerciseSet);
        when(exerciseSetDTOMapper.toDTO(updatedExerciseSet)).thenReturn(updatedExerciseSetDTO);

        //When
        ExerciseSetDTO result = exerciseSetServiceImpl.updateExerciseSetById(updatedExerciseSetDTO, existingExerciseSet.getId());
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(updatedExerciseSetDTO);

        verify(exerciseSetRepository).getExerciseSetById(existingExerciseSet.getId());
        verify(exerciseSetRepository).save(existingExerciseSet);
        verify(exerciseSetDTOMapper).toDTO(updatedExerciseSet);
    }

    @Test
    public void ExerciseSetServiceImpl_UpdateExerciseSetById_ShouldThrowEntityNotFoundException() {
        //Given
        Long exerciseSetId = 9999999L;
        ExerciseSetDTO exerciseSetDTO = ExerciseSetDTO.builder()
                .id(1L)
                .sets(1L)
                .reps(10L)
                .weight(BigDecimal.valueOf(100F))
                .rest(90L)
                .build();
        when(exerciseSetRepository.getExerciseSetById(exerciseSetId)).
                thenReturn(Optional.empty());

        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseSetServiceImpl.updateExerciseSetById(exerciseSetDTO, exerciseSetId);
        });
        assertEquals("ExerciseSet with id [9999999] not found", exception.getMessage());
    }

}

