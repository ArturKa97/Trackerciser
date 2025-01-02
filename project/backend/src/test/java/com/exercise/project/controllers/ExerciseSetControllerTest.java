package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseSetDTO;
import com.exercise.project.services.ExerciseSetService;
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

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExerciseSetController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ExerciseSetControllerTest {

    @MockBean
    private ExerciseSetService exerciseSetService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void ExerciseSetController_AddExerciseSetToExercise_ShouldReturnExerciseSetDTO() throws Exception {
        //Given
        Long exerciseId = 1L;
        ExerciseSetDTO exerciseSetDTO = ExerciseSetDTO.builder()
                .sets(1L)
                .reps(10L)
                .weight(BigDecimal.valueOf(100F))
                .rest(90L)
                .duration(30L)
                .build();

        when(exerciseSetService.addExerciseSetToExercise(exerciseSetDTO, exerciseId)).thenReturn(exerciseSetDTO);
        //When
        ResultActions response = mockMvc.perform(post("/exerciseSet/{exerciseId}", exerciseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseSetDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exerciseSetDTO)));

        verify(exerciseSetService).addExerciseSetToExercise(exerciseSetDTO, exerciseId);
    }

    @Test
    public void ExerciseSetController_RemoveExerciseSetById_ShouldReturnIsOk() throws Exception {
        //Given
        Long exerciseSetId = 1L;
        //When
        ResultActions response = mockMvc.perform(delete("/exerciseSet/{exerciseSetId}", exerciseSetId));
        //Then
        response
                .andExpect(status().isOk());
        verify(exerciseSetService).removeExerciseSetById(exerciseSetId);
    }

    @Test
    public void ExerciseSetController_UpdateExerciseSetById_ShouldReturnUpdatedExerciseSetDTO() throws Exception {
        //Given
        Long exerciseSetId = 1L;
        ExerciseSetDTO updatedExerciseSetDTO = ExerciseSetDTO.builder()
                .sets(1L)
                .reps(1L)
                .weight(BigDecimal.valueOf(10.0F))
                .rest(90L)
                .duration(30L)
                .build();

        when(exerciseSetService.updateExerciseSetById(updatedExerciseSetDTO, exerciseSetId)).thenReturn(updatedExerciseSetDTO);
        //When
        ResultActions response = mockMvc.perform(put("/exerciseSet/{exerciseSetId}", exerciseSetId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedExerciseSetDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedExerciseSetDTO)));
        verify(exerciseSetService).updateExerciseSetById(updatedExerciseSetDTO, exerciseSetId);
    }

}