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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .date(LocalDate.now())
                .build();
        WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(LocalDate.now())
                .build();
        when(workoutSessionDTOMapper.toEntity(workoutSessionDTO)).thenReturn(workoutSession);
        when(workoutSessionRepository.save(workoutSession)).thenReturn(workoutSession);
        when(workoutSessionDTOMapper.toDTO(workoutSession)).thenReturn(workoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.addWorkoutSession(workoutSessionDTO);
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(workoutSessionDTO);
        verify(workoutSessionDTOMapper).toEntity(workoutSessionDTO);
        verify(workoutSessionRepository).save(workoutSession);
        verify(workoutSessionDTOMapper).toDTO(workoutSession);
    }

    @Test
    public void WorkoutSessionServiceImpl_GetWorkoutSessionById_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(LocalDate.now())
                .build();
        WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(LocalDate.now())
                .build();
        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId())).thenReturn(Optional.of(workoutSession));
        when(workoutSessionDTOMapper.toDTO(workoutSession)).thenReturn(workoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.getWorkoutSessionById(workoutSession.getId());
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(workoutSessionDTO);
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSession.getId());
        verify(workoutSessionDTOMapper).toDTO(workoutSession);

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
                .date(LocalDate.now())
                .build();
        WorkoutSession workoutSession2 = WorkoutSession.builder()
                .id(2L)
                .workoutSessionName("Chest Day")
                .date(LocalDate.now())
                .build();
        List<WorkoutSession> workoutSessions = Arrays.asList(workoutSession1, workoutSession2);

        WorkoutSessionDTO workoutSessionDTO1 = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(LocalDate.now())
                .build();
        WorkoutSessionDTO workoutSessionDTO2 = WorkoutSessionDTO.builder()
                .id(2L)
                .workoutSessionName("Chest Day")
                .date(LocalDate.now())
                .build();
        List<WorkoutSessionDTO> workoutSessionsDTOs = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);

        when(workoutSessionRepository.getAllWorkoutSessions()).thenReturn(workoutSessions);
        when(workoutSessionDTOMapper.toDTO(workoutSession1)).thenReturn(workoutSessionDTO1);
        when(workoutSessionDTOMapper.toDTO(workoutSession2)).thenReturn(workoutSessionDTO2);
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessions();
        //Then
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo(workoutSessionsDTOs);
        verify(workoutSessionRepository).getAllWorkoutSessions();
        verify(workoutSessionDTOMapper).toDTO(workoutSession1);
        verify(workoutSessionDTOMapper).toDTO(workoutSession2);
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessions_ShouldReturnAnEmptyList() {
        //Given
        when(workoutSessionRepository.getAllWorkoutSessions()).thenReturn(Collections.emptyList());
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessions();
        //Then
        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(workoutSessionRepository).getAllWorkoutSessions();

    }

    @Test
    public void WorkoutSessionServiceImpl_UpdateWorkoutSessionById_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg Day")
                .date(LocalDate.now())
                .build();

        WorkoutSession updatedWorkoutSession = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Chest Day")
                .date(LocalDate.now())
                .build();

        WorkoutSessionDTO updatedWorkoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Chest Day")
                .date(LocalDate.now())
                .build();

        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId())).thenReturn(Optional.of(workoutSession));
        when(workoutSessionRepository.save(workoutSession)).thenReturn(updatedWorkoutSession);
        when(workoutSessionDTOMapper.toDTO(updatedWorkoutSession)).thenReturn(updatedWorkoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.updateWorkoutSessionById(workoutSession.getId(), updatedWorkoutSessionDTO);
        //Then
        assertThat(result).isNotNull()
                .isEqualTo(updatedWorkoutSessionDTO);
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSession.getId());
        verify(workoutSessionRepository).save(updatedWorkoutSession);
        verify(workoutSessionDTOMapper).toDTO(updatedWorkoutSession);
    }

    @Test
    public void WorkoutSessionServiceImpl_UpdateWorkoutSessionById_ShouldThrowEntityNotFoundException() {
        //Given
        Long nonExistentId = 999999L;
        WorkoutSessionDTO updatedWorkoutSessionDTO = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Chest Day")
                .date(LocalDate.now())
                .build();
        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.updateWorkoutSessionById(nonExistentId, updatedWorkoutSessionDTO);
        });
        assertEquals("WorkoutSession with id [999999] not found", exception.getMessage());
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessionsBetweenDates_ShouldReturnAWorkoutSessionDTOList() {
        //Given
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 10, 1);

        WorkoutSession workoutSession1 = WorkoutSession.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.of(2024, 2, 2))
                .build();
        WorkoutSession workoutSession2 = WorkoutSession.builder()
                .id(2L)
                .workoutSessionName("Arm day")
                .date(LocalDate.of(2024, 8, 8))
                .build();
        List<WorkoutSession> workoutSessions = Arrays.asList(workoutSession1, workoutSession2);

        WorkoutSessionDTO workoutSessionDTO1 = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Leg day")
                .date(LocalDate.of(2024, 2, 2))
                .build();
        WorkoutSessionDTO workoutSessionDTO2 = WorkoutSessionDTO.builder()
                .id(1L)
                .workoutSessionName("Arm day")
                .date(LocalDate.of(2024, 8, 8))
                .build();
        List<WorkoutSessionDTO> workoutSessionDTOS = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);

        when(workoutSessionRepository.getWorkoutSessionsBetweenDates(fromDate, toDate)).thenReturn(workoutSessions);
        when(workoutSessionDTOMapper.toDTO(workoutSession1)).thenReturn(workoutSessionDTO1);
        when(workoutSessionDTOMapper.toDTO(workoutSession2)).thenReturn(workoutSessionDTO2);
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessionsBetweenDates(fromDate, toDate);
        //Then
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo(workoutSessionDTOS);
        verify(workoutSessionRepository).getWorkoutSessionsBetweenDates(fromDate, toDate);
        verify(workoutSessionDTOMapper).toDTO(workoutSession1);
        verify(workoutSessionDTOMapper).toDTO(workoutSession2);
    }
    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessionsBetweenDates_ShouldReturnAnEmptyList() {
        //Given
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 10, 1);
        when(workoutSessionRepository.getWorkoutSessionsBetweenDates(fromDate, toDate)).thenReturn(Collections.emptyList());
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessionsBetweenDates(fromDate, toDate);
        //Then
        assertThat(result).isNotNull()
                .isEmpty();
        verify(workoutSessionRepository).getWorkoutSessionsBetweenDates(fromDate, toDate);
    }
}