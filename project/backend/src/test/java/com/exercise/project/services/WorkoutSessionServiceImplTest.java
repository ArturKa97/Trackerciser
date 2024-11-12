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

import java.util.Date;
import java.util.Optional;

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

}