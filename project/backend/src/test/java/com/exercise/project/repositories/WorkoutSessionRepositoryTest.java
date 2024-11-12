package com.exercise.project.repositories;

import com.exercise.project.entities.WorkoutSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.sql.init.mode=never")
class WorkoutSessionRepositoryTest {

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionById_ShouldReturnWorkoutSessionEntity() {
        //Given
        WorkoutSession workoutSession = WorkoutSession.builder()
                .workoutSessionName("Leg day")
                .date(new Date())
                .build();
        //When
        WorkoutSession savedWorkoutSession = workoutSessionRepository.save(workoutSession);
        WorkoutSession fetchedWorkoutSession = workoutSessionRepository.getWorkoutSessionById(workoutSession.getId()).get();
        //Then
        assertThat(fetchedWorkoutSession).isNotNull();
        assertThat(fetchedWorkoutSession).isEqualTo(savedWorkoutSession);
    }

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionById_ShouldReturnOptionalWhenWorkoutSessionDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When
        Optional<WorkoutSession> fetchedWorkoutSession = workoutSessionRepository.getWorkoutSessionById(nonExistentId);
        //Then
        assertThat(fetchedWorkoutSession).isNotNull();
        assertThat(fetchedWorkoutSession).isEmpty();
    }

}