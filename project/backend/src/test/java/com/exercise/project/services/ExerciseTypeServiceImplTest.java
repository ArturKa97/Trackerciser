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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExerciseTypeServiceImplTest {

    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;

    @Mock
    private ExerciseTypeDTOMapper exerciseTypeDTOMapper;

    @InjectMocks
    private ExerciseTypeServiceImpl exerciseTypeServiceImpl;

    private ExerciseType createExerciseTypeTestEntity(Long id, String name) {
        return ExerciseType.builder()
                .id(id)
                .name(name)
                .build();
    }
    private ExerciseTypeDTO createExerciseTypeTestDTO(Long id, String name) {
        return ExerciseTypeDTO.builder()
                .id(id)
                .name(name)
                .build();
    }

    @Test
    public void ExerciseTypeServiceImpl_GetAllExerciseTypes_ShouldReturnAnExerciseTypeDTOList() {
        //Given
        ExerciseType exerciseType1 = createExerciseTypeTestEntity(1L, "Squats");
        ExerciseType exerciseType2 = createExerciseTypeTestEntity(2L, "Curls");
        List<ExerciseType> exerciseTypes = Arrays.asList(exerciseType1, exerciseType2);

        ExerciseTypeDTO exerciseTypeDTO1 = createExerciseTypeTestDTO(1L, "Squats");
        ExerciseTypeDTO exerciseTypeDTO2 = createExerciseTypeTestDTO(2L, "Curls");
        List<ExerciseTypeDTO> exerciseTypesDTO = Arrays.asList(exerciseTypeDTO1, exerciseTypeDTO2);

        when(exerciseTypeRepository.getAllExerciseTypes()).thenReturn(exerciseTypes);
        when(exerciseTypeDTOMapper.toDTO(exerciseType1)).thenReturn(exerciseTypeDTO1);
        when(exerciseTypeDTOMapper.toDTO(exerciseType2)).thenReturn(exerciseTypeDTO2);
        //When
        List<ExerciseTypeDTO> result = exerciseTypeServiceImpl.getAllExerciseTypes();
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(exerciseTypesDTO);
        verify(exerciseTypeRepository).getAllExerciseTypes();
        verify(exerciseTypeDTOMapper).toDTO(exerciseType1);
        verify(exerciseTypeDTOMapper).toDTO(exerciseType2);
    }
    @Test
    public void ExerciseTypeServiceImpl_GetAllExerciseTypes_ShouldReturnAnEmptyList() {
        //Given
        when(exerciseTypeServiceImpl.getAllExerciseTypes()).thenReturn(Collections.emptyList());
        //When
        List<ExerciseTypeDTO> result = exerciseTypeServiceImpl.getAllExerciseTypes();
        //Then
        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(exerciseTypeRepository).getAllExerciseTypes();
    }
}