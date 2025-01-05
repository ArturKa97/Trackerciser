package com.exercise.project.repositories;

import com.exercise.project.entities.WorkoutSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.sql.init.mode=never")
class
WorkoutSessionRepositoryTest {

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionById_ShouldReturnWorkoutSessionEntity() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .build();
        //When
        WorkoutSession savedWorkoutSession = workoutSessionRepository.save(workoutSession);
        WorkoutSession fetchedWorkoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSession.getId()).get();
        //Then
        assertThat(fetchedWorkoutSession).isNotNull()
                .isEqualTo(savedWorkoutSession);
    }

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionById_ShouldReturnOptionalWhenWorkoutSessionDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When
        Optional<WorkoutSession> fetchedWorkoutSession = workoutSessionRepository.getWorkoutSessionById(nonExistentId);
        //Then
        assertThat(fetchedWorkoutSession).isNotNull()
                .isEmpty();
    }

    @Test
    public void WorkoutSessionRepository_GetAllWorkoutSessions_ShouldReturnListOfWorkoutSessions() {
        //Given
        WorkoutSession workoutSession1 = WorkoutSession.builder()
                .workoutSessionName("Leg day")
                .date(LocalDate.now())
                .build();
        WorkoutSession workoutSession2 = WorkoutSession.builder()
                .workoutSessionName("Arm day")
                .date(LocalDate.now())
                .build();
        //When
        workoutSessionRepository.save(workoutSession1);
        workoutSessionRepository.save(workoutSession2);
        List<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getAllWorkoutSessions();
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(workoutSession1, workoutSession2);
    }

    @Test
    public void WorkoutSessionRepository_GetAllWorkoutSessions_ShouldReturnEmptyList() {
        //When
        List<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getAllWorkoutSessions();
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .isEmpty();
    }

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionsBetweenDates_ShouldReturnWorkoutSessionsInBetweenProvidedDates() {
        //Given
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 10, 1);

        WorkoutSession workoutSession1 = WorkoutSession.builder()
                .workoutSessionName("Leg day")
                .date(LocalDate.of(2024, 2, 2))
                .build();
        WorkoutSession workoutSession2 = WorkoutSession.builder()
                .workoutSessionName("Arm day")
                .date(LocalDate.of(2024, 8, 8))
                .build();
        WorkoutSession notIncludedWorkoutSession = WorkoutSession.builder()
                .workoutSessionName("Chest day")
                .date(LocalDate.of(2024, 12, 12))
                .build();
        //When
        workoutSessionRepository.save(workoutSession1);
        workoutSessionRepository.save(workoutSession2);
        workoutSessionRepository.save(notIncludedWorkoutSession);
        List<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getWorkoutSessionsBetweenDates(fromDate, toDate);
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(workoutSession1, workoutSession2);
    }
    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionsBetweenDates_ShouldReturnEmptyListIfNoWorkoutSessionsWithProvidedDatesArePresent() {
        //Given
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 10, 1);
        //When
        List<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getWorkoutSessionsBetweenDates(fromDate, toDate);
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .isEmpty();
    }

}