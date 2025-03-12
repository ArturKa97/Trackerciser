package com.exercise.project.services;

import com.exercise.project.dtos.ExerciseDTO;
import com.exercise.project.entities.Exercise;
import com.exercise.project.entities.ExerciseType;
import com.exercise.project.entities.WorkoutSession;
import com.exercise.project.mappers.ExerciseDTOMapper;
import com.exercise.project.repositories.ExerciseRepository;
import com.exercise.project.repositories.ExerciseTypeRepository;
import com.exercise.project.repositories.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ExerciseServiceImplTest {

    private final Long NONEXISTENTID = 999999L;
    private final Long USERID = 1L;

    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private ExerciseDTOMapper exerciseDTOMapper;
    @Mock
    private WorkoutSessionRepository workoutSessionRepository;
    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;
    @InjectMocks
    private ExerciseServiceImpl exerciseServiceImpl;
    private Exercise createTestExerciseEntity(Long id) {
        return Exercise.builder()
                .id(id)
                .build();
    }
    private ExerciseDTO createTestExerciseDTO(Long id) {
        return ExerciseDTO.builder()
                .id(id)
                .build();
    }
    private WorkoutSession createWorkoutSessionTestEntity(Long id, String name, LocalDate date, Set<Exercise> exercises) {
        return WorkoutSession.builder()
                .id(id)
                .workoutSessionName(name)
                .date(date)
                .exercisesSet(exercises)
                .build();
    }

    @Test
    public void ExerciseServiceImpl_GetExerciseById_ShouldReturnAnExerciseDTO() {
        //Given
        Exercise exercise = createTestExerciseEntity(1L);
        ExerciseDTO exerciseDTO = createTestExerciseDTO(1L);

        when(exerciseRepository.getExerciseById(exercise.getId())).thenReturn(Optional.of(exercise));
        when(exerciseDTOMapper.toDTO(exercise)).thenReturn(exerciseDTO);
        //When
        ExerciseDTO result = exerciseServiceImpl.getExerciseById(exercise.getId());
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(exerciseDTO);
        verify(exerciseRepository).getExerciseById(exercise.getId());
        verify(exerciseDTOMapper).toDTO(exercise);
    }

    @Test
    public void ExerciseServiceImpl_GetExerciseById_ShouldThrowEntityNotFoundException() {
        //Given
        when(exerciseRepository.getExerciseById(NONEXISTENTID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.getExerciseById(NONEXISTENTID);
        });
        assertEquals("Exercise with id [999999] not found", exception.getMessage());
    }

    @Test
    public void ExerciseServiceImpl_GetAllExercises_ShouldReturnAnExerciseDTOList() {
        //Given
        Exercise exercise1 = createTestExerciseEntity(1L);
        Exercise exercise2 = createTestExerciseEntity(2L);
        List<Exercise> exercises = Arrays.asList(exercise1, exercise2);

        ExerciseDTO exerciseDTO1 = createTestExerciseDTO(1L);
        ExerciseDTO exerciseDTO2 = createTestExerciseDTO(2L);
        List<ExerciseDTO> exercisesDTOs = Arrays.asList(exerciseDTO1, exerciseDTO2);

        when(exerciseRepository.getAllExercises()).thenReturn(exercises);
        when(exerciseDTOMapper.toDTO(exercise1)).thenReturn(exerciseDTO1);
        when(exerciseDTOMapper.toDTO(exercise2)).thenReturn(exerciseDTO2);
        //When
        List<ExerciseDTO> result = exerciseServiceImpl.getAllExercises();
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(exercisesDTOs);
        verify(exerciseRepository).getAllExercises();
        verify(exerciseDTOMapper).toDTO(exercise1);
        verify(exerciseDTOMapper).toDTO(exercise2);
    }

    @Test
    public void ExerciseServiceImpl_GetAllExercises_ShouldReturnAnEmptyList() {
        //Given
        when(exerciseServiceImpl.getAllExercises()).thenReturn(Collections.emptyList());
        //When
        List<ExerciseDTO> result = exerciseServiceImpl.getAllExercises();
        //Then
        assertThat(result)
                .isNotNull()
                .isEmpty();
        verify(exerciseRepository).getAllExercises();
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldReturnAnExerciseDTO() {
        //Given
        Long workoutSessionId = 1L;
        Long exerciseTypeId = 2L;
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(workoutSessionId, "Leg day", LocalDate.now(), new HashSet<>());

        ExerciseType exerciseType = ExerciseType.builder()
                .id(exerciseTypeId)
                .name("Squats")
                .build();

        Exercise exercise = new Exercise();
        Exercise savedExercise = createTestExerciseEntity(1L);
        ExerciseDTO exerciseDTO = createTestExerciseDTO(1L);

        when(workoutSessionRepository.getWorkoutSessionById(workoutSessionId, USERID)).thenReturn(Optional.of(workoutSession));
        when(exerciseTypeRepository.getExerciseTypeById(exerciseTypeId)).thenReturn(Optional.of(exerciseType));
        when(exerciseRepository.save(exercise)).thenReturn(savedExercise);
        when(exerciseDTOMapper.toDTO(savedExercise)).thenReturn(exerciseDTO);

        //When
        ExerciseDTO result = exerciseServiceImpl.addExerciseToWorkoutSession(workoutSessionId, exerciseTypeId, USERID);
        //Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(exerciseDTO);
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSessionId, USERID);
        verify(exerciseTypeRepository).getExerciseTypeById(exerciseTypeId);
        verify(exerciseRepository).save(exercise);
        verify(exerciseDTOMapper).toDTO(savedExercise);
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldThrowEntityNotFoundExceptionWhenWorkoutSessionDoesNotExist() {
        //Given
        Long exerciseTypeId = 1L;
        when(workoutSessionRepository.getWorkoutSessionById(NONEXISTENTID, USERID)).thenReturn(Optional.empty());
        //When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.addExerciseToWorkoutSession(NONEXISTENTID, exerciseTypeId, USERID);
        });
        //Then
        assertEquals("WorkoutSession with id [999999] not found on User with id [1]", exception.getMessage());
        verify(workoutSessionRepository).getWorkoutSessionById(NONEXISTENTID, USERID);
        verifyNoInteractions(exerciseTypeRepository, exerciseRepository, exerciseDTOMapper);
    }

    @Test
    public void ExerciseServiceImpl_AddExerciseToWorkoutSession_ShouldThrowEntityNotFoundExceptionWhenExerciseTypeDoesNotExist() {
        //Given
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(1L, "Leg day", LocalDate.now(), new HashSet<>());
        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId(), USERID)).thenReturn(Optional.of(workoutSession));
        when(exerciseTypeRepository.getExerciseTypeById(NONEXISTENTID)).thenReturn(Optional.empty());
        //When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.addExerciseToWorkoutSession(workoutSession.getId(), NONEXISTENTID, USERID);
        });
        //Then
        assertEquals("ExerciseType with id [999999] not found", exception.getMessage());
        verify(exerciseTypeRepository).getExerciseTypeById(NONEXISTENTID);
        verifyNoInteractions(exerciseRepository, exerciseDTOMapper);
    }

    @Test
    public void ExerciseServiceImpl_RemoveExerciseFromWorkoutSession_ShouldRemoveExerciseFromWorkoutSessionSuccessfully() {
        // Given
        Long workoutSessionId = 1L;
        Long exerciseId = 2L;
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(workoutSessionId, "Leg day", LocalDate.now(), new HashSet<>());

        Exercise exercise = Exercise.builder()
                .id(exerciseId)
                .build();

        when(workoutSessionRepository.getWorkoutSessionById(workoutSessionId, USERID)).thenReturn(Optional.of(workoutSession));
        when(exerciseRepository.getExerciseById(exerciseId)).thenReturn(Optional.of(exercise));
        // When
        exerciseServiceImpl.removeExerciseFromWorkoutSession(workoutSessionId, exerciseId, USERID);
        // Then
        verify(workoutSessionRepository).getWorkoutSessionById(workoutSessionId, USERID);
        verify(exerciseRepository).getExerciseById(exerciseId);
        verify(workoutSessionRepository).save(workoutSession);
        assertFalse(workoutSession.getExercisesSet().contains(exercise));
    }

    @Test
    public void ExerciseServiceImpl_RemoveExerciseFromWorkoutSession_ShouldThrowEntityNotFoundExceptionWhenWorkoutSessionDoesNotExist() {
        //Given
        Long exerciseId = 1L;
        when(workoutSessionRepository.getWorkoutSessionById(NONEXISTENTID, USERID)).thenReturn(Optional.empty());
        //When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.removeExerciseFromWorkoutSession(NONEXISTENTID, exerciseId, USERID);
        });
        //Then
        assertEquals("WorkoutSession with id [999999] not found on User with id [1]", exception.getMessage());
        verify(workoutSessionRepository).getWorkoutSessionById(NONEXISTENTID, USERID);
        verifyNoInteractions(exerciseRepository);
    }

    @Test
    public void ExerciseServiceImpl_RemoveExerciseFromWorkoutSession_ShouldThrowEntityNotFoundExceptionWhenExerciseDoesNotExist() {
        //Given
        WorkoutSession workoutSession = createWorkoutSessionTestEntity(1L, "Leg day", LocalDate.now(), new HashSet<>());
        when(workoutSessionRepository.getWorkoutSessionById(workoutSession.getId(), USERID)).thenReturn(Optional.of(workoutSession));
        when(exerciseRepository.getExerciseById(NONEXISTENTID)).thenReturn(Optional.empty());
        //When
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseServiceImpl.removeExerciseFromWorkoutSession(workoutSession.getId(), NONEXISTENTID, USERID);
        });
        //Then
        assertEquals("Exercise with id [999999] not found", exception.getMessage());
        verify(exerciseRepository).getExerciseById(NONEXISTENTID);
    }
}



