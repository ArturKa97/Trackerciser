package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.WorkoutSessionDTOMapper;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutSessionServiceImplTest {

    @Mock
    private WorkoutSessionRepository workoutSessionRepository;

    @Mock
    private WorkoutSessionDTOMapper workoutSessionDTOMapper;

    @InjectMocks
    private WorkoutSessionServiceImpl workoutSessionServiceImpl;

    @Test
    public void WorkoutSessionServiceImpl_AddWorkoutSession_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();
        WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();
        when(workoutSessionDTOMapper.toEntity(workoutSessionDTO)).thenReturn(workoutSession);
        when(workoutSessionRepository.save(workoutSession)).thenReturn(workoutSession);
        when(workoutSessionDTOMapper.toDTO(workoutSession)).thenReturn(workoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.addWorkoutSession(workoutSessionDTO);
        //Then
        assertNotNull(result);
        assertEquals(result, workoutSessionDTO);
        verify(workoutSessionDTOMapper, times(1)).toEntity(workoutSessionDTO);
        verify(workoutSessionRepository, times(1)).save(workoutSession);
        verify(workoutSessionDTOMapper, times(1)).toDTO(workoutSession);
    }

    @Test
    public void WorkoutSessionServiceImpl_GetWorkoutSessionById_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();
        WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();
        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId())).thenReturn(Optional.of(workoutSession));
        when(workoutSessionDTOMapper.toDTO(workoutSession)).thenReturn(workoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.getWorkoutSessionById(workoutSession.getId());
        //Then
        assertNotNull(result);
        assertEquals(result, workoutSessionDTO);
        verify(workoutSessionRepository, times(1)).getWorkoutSessionById(workoutSession.getId());
        verify(workoutSessionDTOMapper, times(1)).toDTO(workoutSession);

    }

    @Test
    public void WorkoutSessionServiceImpl_GetWorkoutSessionById_ShouldThrowEntityNotFoundException() {
        //Given
        Long nonExistentId = 999999L;
        when(workoutSessionRepository.getWorkoutSessionById(nonExistentId)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.getWorkoutSessionById(nonExistentId);
        });
        assertEquals("WorkoutSession with id [999999] not found", exception.getMessage());
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessions_ShouldReturnAListOfWorkoutSessionDTOS() {
        //Given
        WorkoutSession workoutSession1 = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();
        WorkoutSession workoutSession2 = WorkoutSession.builder()
                .id(2L)
                .workoutSessionName("Chest Day")
                .date(new Date())
                .build();
        List<WorkoutSession> workoutSessions = Arrays.asList(workoutSession1, workoutSession2);

        WorkoutSessionDTO workoutSessionDTO1 = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();
        WorkoutSessionDTO workoutSessionDTO2 = WorkoutSessionDTO.builder()
                .id(2L)
                .workoutSessionName("Chest Day")
                .date(new Date())
                .build();
        List<WorkoutSessionDTO> workoutSessionsDTOs = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);

        when(workoutSessionRepository.getAllWorkoutSessions()).thenReturn(workoutSessions);
        when(workoutSessionDTOMapper.toDTO(workoutSession1)).thenReturn(workoutSessionDTO1);
        when(workoutSessionDTOMapper.toDTO(workoutSession2)).thenReturn(workoutSessionDTO2);
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessions();
        //Then
        assertNotNull(result);
        assertEquals(result, workoutSessionsDTOs);
        verify(workoutSessionRepository, times(1)).getAllWorkoutSessions();
        verify(workoutSessionDTOMapper, times(1)).toDTO(workoutSession1);
        verify(workoutSessionDTOMapper, times(1)).toDTO(workoutSession2);
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessions_ShouldReturnAnEmptyList() {
        //Given
        when(workoutSessionRepository.getAllWorkoutSessions()).thenReturn(Collections.emptyList());
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessions();
        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(workoutSessionRepository, times(1)).getAllWorkoutSessions();

    }

    @Test
    public void WorkoutSessionServiceImpl_UpdateWorkoutSessionById_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(new Date())
                .build();

        WorkoutSession updatedWorkoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Chest Day")
                .date(new Date())
                .build();

        WorkoutSessionDTO updatedWorkoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Chest Day")
                .date(new Date())
                .build();

        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId())).thenReturn(Optional.of(workoutSession));
        when(workoutSessionRepository.save(workoutSession)).thenReturn(updatedWorkoutSession);
        when(workoutSessionDTOMapper.toDTO(updatedWorkoutSession)).thenReturn(updatedWorkoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.updateWorkoutSessionById(workoutSession.getId(), updatedWorkoutSessionDTO);
        //Then
        assertNotNull(result);
        assertEquals(result, updatedWorkoutSessionDTO);
        verify(workoutSessionRepository, times(1)).getWorkoutSessionById(workoutSession.getId());
        verify(workoutSessionRepository, times(1)).save(updatedWorkoutSession);
        verify(workoutSessionDTOMapper, times(1)).toDTO(updatedWorkoutSession);
    }

    @Test
    public void WorkoutSessionServiceImpl_UpdateWorkoutSessionById_ShouldThrowEntityNotFoundException() {
        //Given
        Long nonExistentId = 999999L;
        WorkoutSessionDTO updatedWorkoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Chest Day")
                .date(new Date())
                .build();
        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.updateWorkoutSessionById(nonExistentId, updatedWorkoutSessionDTO);
        });
        assertEquals("WorkoutSession with id [999999] not found", exception.getMessage());
    }

}