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

    private final Long NONEXISTENTID = 999999L;
    @Mock
    private ExerciseSetDTOMapper exerciseSetDTOMapper;
    @Mock
    private ExerciseSetRepository exerciseSetRepository;
    @Mock
    private ExerciseRepository exerciseRepository;
    @InjectMocks
    private ExerciseSetServiceImpl exerciseSetServiceImpl;
    private ExerciseSet createExerciseSetTestEntity(Long id, Long sets, Long reps, Float weight, Long rest, Long duration) {
        return ExerciseSet.builder()
                .id(id)
                .sets(sets)
                .reps(reps)
                .weight(BigDecimal.valueOf(weight))
                .rest(rest)
                .duration(duration)
                .build();
    }
    private ExerciseSetDTO createExerciseSetTestDTO(Long id, Long sets, Long reps, Float weight, Long rest, Long duration) {
        return ExerciseSetDTO.builder()
                .id(id)
                .sets(sets)
                .reps(reps)
                .weight(BigDecimal.valueOf(weight))
                .rest(rest)
                .duration(duration)
                .build();
    }
    @Test
    public void ExerciseSetServiceImpl_AddExerciseSetToExercise_ShouldReturnExerciseSetDTO() {
        //Given
        Exercise exercise = new Exercise();
        ExerciseSet exerciseSet = createExerciseSetTestEntity(1L, 1L, 10L, 100F, 90L, 30L);
        ExerciseSetDTO exerciseSetDTO = createExerciseSetTestDTO(1L, 1L, 10L, 100F, 90L, 30L);

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
        ExerciseSetDTO exerciseSetDTO = createExerciseSetTestDTO(1L, 1L, 10L, 100F, 90L, 30L);
        when(exerciseRepository.getExerciseById(NONEXISTENTID)).
                thenReturn(Optional.empty());

        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseSetServiceImpl.addExerciseSetToExercise(exerciseSetDTO, NONEXISTENTID);
        });
        assertEquals("Exercise with id [999999] not found", exception.getMessage());
    }

    @Test
    public void ExerciseSetServiceImpl_UpdateExerciseSetById_ShouldReturnUpdatedExerciseSetDTO() {
        //Given
        ExerciseSet existingExerciseSet = createExerciseSetTestEntity(1L, 1L, 10L, 100F, 90L, 30L);
        ExerciseSet updatedExerciseSet = createExerciseSetTestEntity(1L, 2L, 20L, 200F, 100L, 40L);
        ExerciseSetDTO updatedExerciseSetDTO = createExerciseSetTestDTO(1L, 2L, 20L, 200F, 100L, 40L);

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
        ExerciseSetDTO exerciseSetDTO = createExerciseSetTestDTO(1L, 1L, 10L, 100F, 90L, 30L);
        when(exerciseSetRepository.getExerciseSetById(NONEXISTENTID)).
                thenReturn(Optional.empty());

        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseSetServiceImpl.updateExerciseSetById(exerciseSetDTO, NONEXISTENTID);
        });
        assertEquals("ExerciseSet with id [999999] not found", exception.getMessage());
    }

}

