package com.exercise.project.repositories;

import com.exercise.project.entities.User;
import com.exercise.project.entities.WorkoutSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class WorkoutSessionRepositoryTest {

    private final Long NONEXISTENTID = 999999L;
    private final LocalDate FROMDATE = LocalDate.of(2024, 1, 1);
    private final LocalDate TODATE = LocalDate.of(2024, 10, 1);
    private final Long USERID = 1L;
    private final Pageable PAGEABLE = PageRequest.of(0, 10);
    private final User USERENTITY = User.builder().username("user").password("Useris97!").build();

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private UserRepository userRepository;

    private WorkoutSession createWorkoutSessionTestEntity(String workoutSessionName, LocalDate date) {
        return WorkoutSession.builder()
                .workoutSessionName(workoutSessionName)
                .date(date)
                .build();
    }

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionById_ShouldReturnWorkoutSessionEntity() {
        //Given
        User user = userRepository.save(USERENTITY);
        WorkoutSession workoutSession = createWorkoutSessionTestEntity("Leg day", LocalDate.now());
        workoutSession.setUser(user);
        //When
        WorkoutSession savedWorkoutSession = workoutSessionRepository.save(workoutSession);
        WorkoutSession fetchedWorkoutSession = workoutSessionRepository.getWorkoutSessionById(savedWorkoutSession.getId(), user.getId()).get();
        //Then
        assertThat(fetchedWorkoutSession).isNotNull()
                .isEqualTo(savedWorkoutSession);
    }

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionById_ShouldReturnOptionalWhenWorkoutSessionDoesNotExist() {
        //When
        Optional<WorkoutSession> fetchedWorkoutSession = workoutSessionRepository.getWorkoutSessionById(NONEXISTENTID, USERID);
        //Then
        assertThat(fetchedWorkoutSession).isNotNull()
                .isEmpty();
    }

    @Test
    public void WorkoutSessionRepository_GetAllWorkoutSessions_ShouldReturnPageOfWorkoutSessions() {
        //Given
        User user = userRepository.save(USERENTITY);
        WorkoutSession workoutSession1 = createWorkoutSessionTestEntity("Leg day", LocalDate.now());
        WorkoutSession workoutSession2 = createWorkoutSessionTestEntity("Arm day", LocalDate.now());
        workoutSession1.setUser(user);
        workoutSession2.setUser(user);
        //When
        workoutSessionRepository.save(workoutSession1);
        workoutSessionRepository.save(workoutSession2);
        Page<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getAllWorkoutSessions(PAGEABLE, user.getId());
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(workoutSession1, workoutSession2);
    }

    @Test
    public void WorkoutSessionRepository_GetAllWorkoutSessions_ShouldReturnEmptyList() {
        //When
        Page<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getAllWorkoutSessions(PAGEABLE, USERID);
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .isEmpty();
    }

    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionsBetweenDates_ShouldReturnWorkoutSessionsInBetweenProvidedDates() {
        //Given
        User user = userRepository.save(USERENTITY);
        WorkoutSession workoutSession1 = createWorkoutSessionTestEntity("Leg day", LocalDate.of(2024, 2, 2));
        WorkoutSession workoutSession2 = createWorkoutSessionTestEntity("Arm day", LocalDate.of(2024, 8, 8));
        WorkoutSession notIncludedWorkoutSession = createWorkoutSessionTestEntity("Chest day", LocalDate.of(2024, 12, 12));
        workoutSession1.setUser(user);
        workoutSession2.setUser(user);
        notIncludedWorkoutSession.setUser(user);
        //When
        workoutSessionRepository.save(workoutSession1);
        workoutSessionRepository.save(workoutSession2);
        workoutSessionRepository.save(notIncludedWorkoutSession);
        List<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getWorkoutSessionsBetweenDates(FROMDATE, TODATE, user.getId());
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(workoutSession1, workoutSession2);
    }
    @Test
    public void WorkoutSessionRepository_GetWorkoutSessionsBetweenDates_ShouldReturnEmptyListIfNoWorkoutSessionsWithProvidedDatesArePresent() {
        //Given
        //When
        List<WorkoutSession> fetchedWorkoutSessions = workoutSessionRepository.getWorkoutSessionsBetweenDates(FROMDATE, TODATE, USERID);
        //Then
        assertThat(fetchedWorkoutSessions).isNotNull()
                .isEmpty();
    }

}