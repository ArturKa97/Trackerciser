package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseTypeDTO;
import com.exercise.project.entities.ExerciseType;
import com.exercise.project.mappers.ExerciseTypeDTOMapper;
import com.exercise.project.repositories.ExerciseTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExerciseTypeServiceImplTest {

    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;

    @Mock
    private ExerciseTypeDTOMapper exerciseTypeDTOMapper;

    @InjectMocks
    private ExerciseTypeServiceImpl exerciseTypeServiceImpl;

    @Test
    public void ExerciseTypeServiceImpl_GetAllExerciseTypes_ShouldReturnAnExerciseTypeDTOList() {
        //Given
        ExerciseType exerciseType1 = ExerciseType.builder()
                .id(1L)
                .name("Squats")
                .build();

        ExerciseType exerciseType2 = ExerciseType.builder()
                .id(2L)
                .name("Curls")
                .build();

        List<ExerciseType> exerciseTypes = Arrays.asList(exerciseType1, exerciseType2);

        ExerciseTypeDTO exerciseTypeDTO1 = ExerciseTypeDTO.builder()
                .id(1L)
                .name("Squats")
                .build();
        ExerciseTypeDTO exerciseTypeDTO2 = ExerciseTypeDTO.builder()
                .id(2L)
                .name("Curls")
                .build();
        List<ExerciseTypeDTO> exerciseTypesDTO = Arrays.asList(exerciseTypeDTO1, exerciseTypeDTO2);

        when(exerciseTypeRepository.getAllExerciseTypes()).thenReturn(exerciseTypes);
        when(exerciseTypeDTOMapper.toDTO(exerciseType1)).thenReturn(exerciseTypeDTO1);
        when(exerciseTypeDTOMapper.toDTO(exerciseType2)).thenReturn(exerciseTypeDTO2);
        //When
        List<ExerciseTypeDTO> result = exerciseTypeServiceImpl.getAllExerciseTypes();
        //Then
        assertNotNull(result);
        assertEquals(exerciseTypesDTO, result);
        verify(exerciseTypeRepository, times(1)).getAllExerciseTypes();
        verify(exerciseTypeDTOMapper, times(1)).toDTO(exerciseType1);
        verify(exerciseTypeDTOMapper, times(1)).toDTO(exerciseType2);
    }
    @Test
    public void ExerciseTypeServiceImpl_GetAllExerciseTypes_ShouldReturnAnEmptyList() {
        //Given
        when(exerciseTypeServiceImpl.getAllExerciseTypes()).thenReturn(Collections.emptyList());
        //When
        List<ExerciseTypeDTO> result = exerciseTypeServiceImpl.getAllExerciseTypes();
        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(exerciseTypeRepository, times(1)).getAllExerciseTypes();
    }
}