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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void ExerciseSetController_AddExerciseSetToExercise_ShouldReturnIsOk() throws Exception {
        //Given
        Long exerciseId = 1L;
        ExerciseSetDTO exerciseSetDTO = ExerciseSetDTO.builder()
                .sets(1L)
                .reps(10L)
                .weight(100F)
                .rest(90L)
                .build();

        given(exerciseSetService.addExerciseSetToExercise(any(ExerciseSetDTO.class), eq(exerciseId)))
                .willReturn(exerciseSetDTO);
        //When
        ResultActions response = mockMvc.perform(post("/exerciseSet/{exerciseId}", exerciseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseSetDTO)));

        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exerciseSetDTO)));

        //Then
        verify(exerciseSetService, times(1)).addExerciseSetToExercise(any(ExerciseSetDTO.class), eq(exerciseId));

    }

}