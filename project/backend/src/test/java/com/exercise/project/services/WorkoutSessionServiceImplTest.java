package com.exercise.project.services;

import com.exercise.project.dtos.WorkoutSessionDTO;
import com.exercise.project.entities.User;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.WorkoutSessionDTOMapper;
import com.exercise.project.repositories.UserRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class WorkoutSessionServiceImplTest {

    private final Long NONEXISTENTID = 999999L;
    private final LocalDate FROMDATE = LocalDate.of(2024, 1, 1);
    private final LocalDate TODATE = LocalDate.of(2024, 10, 1);
    private final Long USERID = 1L;
    private final Pageable PAGEABLE = PageRequest.of(0, 10);

    @Mock
    private WorkoutSessionRepository workoutSessionRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private WorkoutSessionDTOMapper workoutSessionDTOMapper;
    @InjectMocks
    private WorkoutSessionServiceImpl workoutSessionServiceImpl;
    private WorkoutSession createWorkoutSessionTestEntity(Long id, String name, LocalDate date) {
        return WorkoutSession.builder()
                .id(id)
                .workoutSessionName(name)
                .date(date)
                .build();
    }
    private WorkoutSessionDTO createWorkoutSessionTestDTO(Long id, String name, LocalDate date) {
        return WorkoutSessionDTO.builder()
                .id(id)
                .workoutSessionName(name)
                .date(date)
                .build();
    }
    @Test
    public void WorkoutSessionServiceImpl_AddWorkoutSession_ShouldReturnWorkoutSessionDTO() {
        //Given
        User user = User.builder().id(USERID).userWorkoutSessions(new ArrayList<>()).build();
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(1L, "Leg Day", LocalDate.now());
        WorkoutSessionDTO workoutSessionDTO = createWorkoutSessionTestDTO(1L, "Leg Day", LocalDate.now());
        when(userRepository.getUserById(USERID)).thenReturn(Optional.of(user));
        when(workoutSessionDTOMapper.toEntity(workoutSessionDTO)).thenReturn(workoutSession);
        when(workoutSessionRepository.save(workoutSession)).thenReturn(workoutSession);
        when(workoutSessionDTOMapper.toDTO(workoutSession)).thenReturn(workoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.addWorkoutSession(workoutSessionDTO, USERID);
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(workoutSessionDTO);
        verify(workoutSessionDTOMapper).toEntity(workoutSessionDTO);
        verify(workoutSessionRepository).save(workoutSession);
        verify(workoutSessionDTOMapper).toDTO(workoutSession);
    }

    @Test
    public void WorkoutSessionServiceImpl_AddWorkoutSession_ShouldReturnEntityNotFoundExceptionWhenUserDoesNotExist() {
        //Given
        WorkoutSessionDTO workoutSessionDTO = createWorkoutSessionTestDTO(1L, "Leg Day", LocalDate.now());
        when(userRepository.getUserById(USERID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.addWorkoutSession(workoutSessionDTO, USERID);
        });
        assertEquals("User with id [1] not found", exception.getMessage());
    }

    @Test
    public void WorkoutSessionServiceImpl_GetWorkoutSessionById_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(1L, "Leg Day", LocalDate.now());
        WorkoutSessionDTO workoutSessionDTO = createWorkoutSessionTestDTO(1L, "Leg Day", LocalDate.now());
        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId(), USERID)).thenReturn(Optional.of(workoutSession));
        when(workoutSessionDTOMapper.toDTO(workoutSession)).thenReturn(workoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.getWorkoutSessionById(workoutSession.getId(), USERID);
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(workoutSessionDTO);
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSession.getId(), USERID);
        verify(workoutSessionDTOMapper).toDTO(workoutSession);

    }

    @Test
    public void WorkoutSessionServiceImpl_GetWorkoutSessionById_ShouldThrowEntityNotFoundException() {
        //Given
        when(workoutSessionRepository.getWorkoutSessionById(NONEXISTENTID, USERID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.getWorkoutSessionById(NONEXISTENTID, USERID);
        });
        assertEquals("WorkoutSession with id [999999] not found on User with id [1]", exception.getMessage());
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessions_ShouldReturnAListOfWorkoutSessionDTOS() {
        //Given
        WorkoutSession workoutSession1 = createWorkoutSessionTestEntity(1L, "Leg Day", LocalDate.now());
        WorkoutSession workoutSession2 = createWorkoutSessionTestEntity(2L, "Chest Day", LocalDate.now());
        List<WorkoutSession> workoutSessions = Arrays.asList(workoutSession1, workoutSession2);
        Page<WorkoutSession> workoutSessionsPage = new PageImpl<>(workoutSessions, PAGEABLE, workoutSessions.size());

        WorkoutSessionDTO workoutSessionDTO1 = createWorkoutSessionTestDTO(1L, "Leg Day", LocalDate.now());
        WorkoutSessionDTO workoutSessionDTO2 = createWorkoutSessionTestDTO(2L, "Chest Day", LocalDate.now());
        List<WorkoutSessionDTO> workoutSessionsDTOs = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);
        Page<WorkoutSessionDTO> workoutSessionDTOPage = new PageImpl<>(workoutSessionsDTOs, PAGEABLE, workoutSessionsDTOs.size());

        when(workoutSessionRepository.getAllWorkoutSessions(PAGEABLE, USERID)).thenReturn(workoutSessionsPage);
        when(workoutSessionDTOMapper.toDTO(workoutSession1)).thenReturn(workoutSessionDTO1);
        when(workoutSessionDTOMapper.toDTO(workoutSession2)).thenReturn(workoutSessionDTO2);
        //When
        Page<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessions(PAGEABLE, USERID);
        //Then
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo(workoutSessionDTOPage);
        verify(workoutSessionRepository).getAllWorkoutSessions(PAGEABLE, USERID);
        verify(workoutSessionDTOMapper).toDTO(workoutSession1);
        verify(workoutSessionDTOMapper).toDTO(workoutSession2);
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessions_ShouldReturnEntityNotFoundExceptionIfNoWorkoutSessionsWereFound() {
        //Given
        Page<WorkoutSession> workoutSessionDTOPage = new PageImpl<>(Collections.emptyList(), PAGEABLE, 0);
        when(workoutSessionRepository.getAllWorkoutSessions(PAGEABLE, USERID)).thenReturn(workoutSessionDTOPage);
        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.getAllWorkoutSessions(PAGEABLE, USERID);
        });
        assertEquals("No Workout Sessions found on User with id [1]", exception.getMessage());
    }

    @Test
    public void WorkoutSessionServiceImpl_UpdateWorkoutSessionById_ShouldReturnWorkoutSessionDTO() {
        //Given
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(1L, "Leg Day", LocalDate.now());
        WorkoutSession updatedWorkoutSession = createWorkoutSessionTestEntity(1L, "Chest Day", LocalDate.now());
        WorkoutSessionDTO updatedWorkoutSessionDTO = createWorkoutSessionTestDTO(1L, "Chest Day", LocalDate.now());

        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId(), USERID)).thenReturn(Optional.of(workoutSession));
        when(workoutSessionRepository.save(workoutSession)).thenReturn(updatedWorkoutSession);
        when(workoutSessionDTOMapper.toDTO(updatedWorkoutSession)).thenReturn(updatedWorkoutSessionDTO);
        //When
        WorkoutSessionDTO result = workoutSessionServiceImpl.updateWorkoutSessionById(workoutSession.getId(), USERID, updatedWorkoutSessionDTO);
        //Then
        assertThat(result).isNotNull()
                .isEqualTo(updatedWorkoutSessionDTO);
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSession.getId(), USERID);
        verify(workoutSessionRepository).save(updatedWorkoutSession);
        verify(workoutSessionDTOMapper).toDTO(updatedWorkoutSession);
    }

    @Test
    public void WorkoutSessionServiceImpl_UpdateWorkoutSessionById_ShouldThrowEntityNotFoundException() {
        //Given
        WorkoutSessionDTO updatedWorkoutSessionDTO = createWorkoutSessionTestDTO(1L, "Chest Day", LocalDate.now());
        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.updateWorkoutSessionById(NONEXISTENTID, USERID, updatedWorkoutSessionDTO);
        });
        assertEquals("WorkoutSession with id [999999] not found on User with id [1]", exception.getMessage());
    }

    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessionsBetweenDates_ShouldReturnAWorkoutSessionDTOList() {
        //Given
        WorkoutSession workoutSession1 = createWorkoutSessionTestEntity(1L, "Leg day", LocalDate.of(2024, 2, 2));
        WorkoutSession workoutSession2 = createWorkoutSessionTestEntity(2L, "Arm day", LocalDate.of(2024, 8, 8));
        List<WorkoutSession> workoutSessions = Arrays.asList(workoutSession1, workoutSession2);

        WorkoutSessionDTO workoutSessionDTO1 = createWorkoutSessionTestDTO(1L, "Leg day", LocalDate.of(2024, 2, 2));
        WorkoutSessionDTO workoutSessionDTO2 = createWorkoutSessionTestDTO(2L, "Arm day", LocalDate.of(2024, 8, 8));
        List<WorkoutSessionDTO> workoutSessionDTOS = Arrays.asList(workoutSessionDTO1, workoutSessionDTO2);

        when(workoutSessionRepository.getWorkoutSessionsBetweenDates(FROMDATE, TODATE, USERID)).thenReturn(workoutSessions);
        when(workoutSessionDTOMapper.toDTO(workoutSession1)).thenReturn(workoutSessionDTO1);
        when(workoutSessionDTOMapper.toDTO(workoutSession2)).thenReturn(workoutSessionDTO2);
        //When
        List<WorkoutSessionDTO> result = workoutSessionServiceImpl.getAllWorkoutSessionsBetweenDates(FROMDATE, TODATE, USERID);
        //Then
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo(workoutSessionDTOS);
        verify(workoutSessionRepository).getWorkoutSessionsBetweenDates(FROMDATE, TODATE, USERID);
        verify(workoutSessionDTOMapper).toDTO(workoutSession1);
        verify(workoutSessionDTOMapper).toDTO(workoutSession2);
    }
    @Test
    public void WorkoutSessionServiceImpl_GetAllWorkoutSessionsBetweenDates_ShouldReturnEntityNotFoundExceptionWhenNoWorkoutSessionsWereFound() {
        //Given
        when(workoutSessionRepository.getWorkoutSessionsBetweenDates(FROMDATE, TODATE, USERID)).thenReturn(Collections.emptyList());
        // When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            workoutSessionServiceImpl.getAllWorkoutSessionsBetweenDates(FROMDATE, TODATE, USERID);
        });
        assertEquals("No Workout Sessions found from [%s] to [%s] on User with id [%s] ".formatted(FROMDATE, TODATE, USERID), exception.getMessage());
    }
}