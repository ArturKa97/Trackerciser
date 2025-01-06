package com.exercise.project.controllers;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.services.WorkoutSessionService;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WorkoutSessionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class WorkoutSessionControllerTest {

    @MockBean
    private WorkoutSessionService workoutSessionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void WorkoutSessionController_AddWorkoutSession_ShouldReturnWorkoutSessionDTO() throws Exception {
        //Given
        WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .build();

        when(workoutSessionService.addWorkoutSession(workoutSessionDTO)).thenReturn(workoutSessionDTO);
        //When
        ResultActions response = mockMvc.perform(post("/workout_session")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(workoutSessionDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTO)));

        verify(workoutSessionService).addWorkoutSession(workoutSessionDTO);
    }

    @Test
    public void WorkoutSessionController_GetWorkoutSessionById_ShouldReturnWorkoutSessionDTO() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .build();

        when(workoutSessionService.getWorkoutSessionById(workoutSessionId)).thenReturn(workoutSessionDTO);
        //When
        ResultActions response = mockMvc.perform(get("/workout_session/{id}", workoutSessionId)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTO)));

        verify(workoutSessionService).getWorkoutSessionById(workoutSessionId);
    }

    @Test
    public void WorkoutSessionController_GetAllWorkoutSessions_ShouldReturnWorkoutSessionDTOList() throws Exception {
        //Given
        WorkoutSessionDTO workoutSessionDTO1 = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .build();

        WorkoutSessionDTO workoutSessionDTO2 = WorkoutSessionDTO.builder()
                .id(2L)
                .workoutSessionName("Arm day")
                .date(LocalDate.now())
                .build();

        List<WorkoutSessionDTO> workoutSessionDTOList = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);

        when(workoutSessionService.getAllWorkoutSessions()).thenReturn(workoutSessionDTOList);
        //When
        ResultActions response = mockMvc.perform(get("/workout_session")
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTOList)));

        verify(workoutSessionService).getAllWorkoutSessions();
    }

    @Test
    public void WorkoutSessionController_GetAllWorkoutSessionsBetweenDates_ShouldReturnWorkoutSessionDTOList() throws Exception {
        //Given
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 10, 1);

        WorkoutSessionDTO workoutSessionDTO1 = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.of(2024, 2, 2))
                .build();

        WorkoutSessionDTO workoutSessionDTO2 = WorkoutSessionDTO.builder()
                .id(2L)
                .workoutSessionName("Arm day")
                .date(LocalDate.of(2024, 8, 8))
                .build();

        List<WorkoutSessionDTO> workoutSessionDTOS = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);
        when(workoutSessionService.getAllWorkoutSessionsBetweenDates(fromDate, toDate)).thenReturn(workoutSessionDTOS);

        //When
        ResultActions response = mockMvc.perform(get("/workout_session/dates")
                .param("fromDate", fromDate.toString())
                .param("toDate", toDate.toString())
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTOS)));
        verify(workoutSessionService).getAllWorkoutSessionsBetweenDates(fromDate, toDate);
    }

    @Test
    public void WorkoutSessionController_DeleteWorkoutSessionById_ShouldReturnIsOk() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        //When
        ResultActions response = mockMvc.perform(delete("/workout_session/{id}", workoutSessionId));
        //Then
        response
                .andExpect(status().isOk());
        verify(workoutSessionService).deleteWorkoutSessionById(workoutSessionId);
    }

    @Test
    public void WorkoutSessionController_UpdateWorkoutSessionById_ShouldReturnUpdatedWorkoutSessionDTO() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        WorkoutSessionDTO updatedWorkoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .build();

        when(workoutSessionService.updateWorkoutSessionById(workoutSessionId, updatedWorkoutSessionDTO)).thenReturn(updatedWorkoutSessionDTO);
        //When
        ResultActions response = mockMvc.perform(put("/workout_session/{workoutSessionId}", workoutSessionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWorkoutSessionDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedWorkoutSessionDTO)));

        verify(workoutSessionService).updateWorkoutSessionById(workoutSessionId, updatedWorkoutSessionDTO);
    }

}