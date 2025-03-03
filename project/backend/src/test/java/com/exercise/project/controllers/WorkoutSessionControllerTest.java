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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private final Long USERID = 1L;
    private final Pageable PAGEABLE = PageRequest.of(0, 10);

    @MockBean
    private WorkoutSessionService workoutSessionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private WorkoutSessionDTO createWorkoutSessionTestDTO(Long id, String name, LocalDate date) {
        return WorkoutSessionDTO.builder()
                .id(id)
                .workoutSessionName(name)
                .date(date)
                .build();
    }

    @Test
    public void WorkoutSessionController_AddWorkoutSession_ShouldReturnWorkoutSessionDTO() throws Exception {
        //Given
        WorkoutSessionDTO workoutSessionDTO = createWorkoutSessionTestDTO(1L, "Leg day", LocalDate.now());

        when(workoutSessionService.addWorkoutSession(workoutSessionDTO, USERID)).thenReturn(workoutSessionDTO);
        //When
        ResultActions response = mockMvc.perform(post("/workout_session/{userId}", USERID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(workoutSessionDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTO)));

        verify(workoutSessionService).addWorkoutSession(workoutSessionDTO, USERID);
    }

    @Test
    public void WorkoutSessionController_GetWorkoutSessionById_ShouldReturnWorkoutSessionDTO() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        WorkoutSessionDTO workoutSessionDTO = createWorkoutSessionTestDTO(1L, "Leg day", LocalDate.now());

        when(workoutSessionService.getWorkoutSessionById(workoutSessionId, USERID)).thenReturn(workoutSessionDTO);
        //When
        ResultActions response = mockMvc.perform(get("/workout_session/{id}/{userId}", workoutSessionId, USERID)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTO)));

        verify(workoutSessionService).getWorkoutSessionById(workoutSessionId, USERID);
    }

    @Test
    public void WorkoutSessionController_GetAllWorkoutSessions_ShouldReturnWorkoutSessionDTOList() throws Exception {
        //Given
        WorkoutSessionDTO workoutSessionDTO1 = createWorkoutSessionTestDTO(1L, "Leg day", LocalDate.now());
        WorkoutSessionDTO workoutSessionDTO2 = createWorkoutSessionTestDTO(2L, "Arm day", LocalDate.now());

        List<WorkoutSessionDTO> workoutSessionDTOList = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);
        Page<WorkoutSessionDTO> workoutSessionDTOPage = new PageImpl<>(workoutSessionDTOList, PAGEABLE, workoutSessionDTOList.size());

        when(workoutSessionService.getAllWorkoutSessions(PAGEABLE, USERID)).thenReturn(workoutSessionDTOPage);
        //When
        ResultActions response = mockMvc.perform(get("/workout_session/{userId}", USERID)
                .param("page", String.valueOf(PAGEABLE.getPageNumber()))
                .param("size", String.valueOf(PAGEABLE.getPageSize()))
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTOPage)));

        verify(workoutSessionService).getAllWorkoutSessions(PAGEABLE, USERID);
    }

    @Test
    public void WorkoutSessionController_GetAllWorkoutSessionsBetweenDates_ShouldReturnWorkoutSessionDTOList() throws Exception {
        //Given
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 10, 1);

        WorkoutSessionDTO workoutSessionDTO1 = createWorkoutSessionTestDTO(1L, "Leg day", LocalDate.of(2024, 2, 2));
        WorkoutSessionDTO workoutSessionDTO2 = createWorkoutSessionTestDTO(2L, "Arm day", LocalDate.of(2024, 8, 8));
        List<WorkoutSessionDTO> workoutSessionDTOS = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);

        when(workoutSessionService.getAllWorkoutSessionsBetweenDates(fromDate, toDate, USERID)).thenReturn(workoutSessionDTOS);
        //When
        ResultActions response = mockMvc.perform(get("/workout_session/dates/{userId}", USERID)
                .param("fromDate", fromDate.toString())
                .param("toDate", toDate.toString())
                .contentType(MediaType.APPLICATION_JSON));

        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(workoutSessionDTOS)));
        verify(workoutSessionService).getAllWorkoutSessionsBetweenDates(fromDate, toDate, USERID);
    }

    @Test
    public void WorkoutSessionController_DeleteWorkoutSessionById_ShouldReturnIsOk() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        //When
        ResultActions response = mockMvc.perform(delete("/workout_session/{id}/{userId}", workoutSessionId, USERID));
        //Then
        response
                .andExpect(status().isOk());
        verify(workoutSessionService).deleteWorkoutSessionById(workoutSessionId, USERID);
    }

    @Test
    public void WorkoutSessionController_UpdateWorkoutSessionById_ShouldReturnUpdatedWorkoutSessionDTO() throws Exception {
        //Given
        Long workoutSessionId = 1L;
        WorkoutSessionDTO updatedWorkoutSessionDTO = createWorkoutSessionTestDTO(1L, "Leg day", LocalDate.now());

        when(workoutSessionService.updateWorkoutSessionById(workoutSessionId, USERID, updatedWorkoutSessionDTO)).thenReturn(updatedWorkoutSessionDTO);
        //When
        ResultActions response = mockMvc.perform(put("/workout_session/{id}/{userId}", workoutSessionId, USERID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWorkoutSessionDTO)));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedWorkoutSessionDTO)));

        verify(workoutSessionService).updateWorkoutSessionById(workoutSessionId, USERID, updatedWorkoutSessionDTO);
    }

}