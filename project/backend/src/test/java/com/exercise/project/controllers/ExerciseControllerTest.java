package com.exercise.project.controllers;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.services.ExerciseService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExerciseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ExerciseControllerTest {

    @MockBean
    private ExerciseService exerciseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void ExerciseController_AddExercise_ShouldReturnIsOk() throws Exception {
        //Given
        Exercise exercise = Exercise.builder()
                .id(1L)
                .build();
        //When
        ResultActions response = mockMvc.perform(post("/exercise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exercise)));
        //Then
        response
                .andExpect(status().isOk());
        verify(exerciseService).addExercise(exercise);
    }

    @Test
    public void ExerciseController_GetExerciseById_ShouldReturnExerciseDTO() throws Exception {
        //Given
        Long exerciseId = 1L;
        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .id(1L)
                .build();

        when(exerciseService.getExerciseById(exerciseId)).thenReturn(exerciseDTO);
        //When
        ResultActions response = mockMvc.perform(get("/exercise/{id}", exerciseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exerciseDTO)));

        verify(exerciseService).getExerciseById(exerciseId);
    }

    @Test
    public void ExerciseController_GetAllExercises_ShouldReturnExerciseDTOList() throws Exception {
        //Given
        ExerciseDTO exerciseDTO1 = ExerciseDTO.builder()
                .id(1L)
                .build();
        ExerciseDTO exerciseDTO2 = ExerciseDTO.builder()
                .id(2L)
                .build();
        List<ExerciseDTO> exerciseDTOList = Arrays.asList(exerciseDTO1, exerciseDTO2);

        when(exerciseService.getAllExercises()).thenReturn(exerciseDTOList);
        //When
        ResultActions response = mockMvc.perform(get("/exercise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseDTOList)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exerciseDTOList)));

        verify(exerciseService).getAllExercises();
    }

    @Test
    public void ExerciseController_DeleteExerciseById_ShouldReturnIsOk() throws Exception {
        //Given
        Long exerciseId = 1L;
        //When
        ResultActions response = mockMvc.perform(delete("/exercise/{id}", exerciseId));
        //Then
        response
                .andExpect(status().isOk());

        verify(exerciseService).deleteExerciseById(exerciseId);
    }

    @Test
    public void ExerciseController_AddExerciseToWorkoutSession_ShouldReturnExerciseDTO() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        Long exerciseTypeId = 1L;
        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .id(1L)
                .build();

        when(exerciseService.addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId)).thenReturn(exerciseDTO);
        //When
        ResultActions response = mockMvc.perform(post("/exercise/{workoutSessionId}/{exerciseTypeId}", workoutSessionId, exerciseTypeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exerciseDTO)));

        verify(exerciseService).addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId);
    }

}