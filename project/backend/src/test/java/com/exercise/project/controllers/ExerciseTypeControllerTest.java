package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseTypeDTO;
import com.exercise.project.services.ExerciseTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExerciseTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ExerciseTypeControllerTest {

    @MockBean
    private ExerciseTypeService exerciseTypeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ExerciseTypeDTO createExerciseTypeTestDTO(Long id, String name) {
        return ExerciseTypeDTO.builder()
                .id(id)
                .name(name)
                .build();
    }

    @Test
    public void ExerciseTypeController_GetAllExerciseTypes() throws Exception {
        //Given
        ExerciseTypeDTO exerciseTypeDTO1 = createExerciseTypeTestDTO(1L, "Squats");
        ExerciseTypeDTO exerciseTypeDTO2 = createExerciseTypeTestDTO(2L, "Curls");
        List<ExerciseTypeDTO> exerciseTypeDTOList = Arrays.asList(exerciseTypeDTO1, exerciseTypeDTO2);

        when(exerciseTypeService.getAllExerciseTypes()).thenReturn(exerciseTypeDTOList);
        //When
        ResultActions response = mockMvc.perform(get("/exercise_type")
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exerciseTypeDTOList)));

        verify(exerciseTypeService).getAllExerciseTypes();
    }

}