package com.exercise.project.repositories;

import com.exercise.project.entities.Exercise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(properties = "spring.sql.init.mode=never")
class ExerciseRepositoryTest {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Test
    public void ExerciseRepository_GetExerciseById_ShouldReturnAnExerciseEntity() {
        //Given
        Exercise exercise = new Exercise();
        //When
        Exercise savedExercise = exerciseRepository.save(exercise);
        Exercise fetchedExercise = exerciseRepository.getExerciseById(savedExercise.getId()).get();
        //Then
        assertThat(fetchedExercise).isNotNull()
                .isEqualTo(savedExercise);
    }

    @Test
    public void ExerciseRepository_GetExerciseById_ShouldReturnAnOptionalWhenExerciseDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When
        Optional<Exercise> fetchedExercise = exerciseRepository.getExerciseById(nonExistentId);
        //Then
        assertThat(fetchedExercise).isNotNull()
                .isEmpty();
    }

    @Test
    public void ExerciseRepository_GetAllExercises_ShouldReturnAnExerciseList() {
        //Given
        Exercise exercise1 = new Exercise();
        Exercise exercise2 = new Exercise();
        //When
        exerciseRepository.save(exercise1);
        exerciseRepository.save(exercise2);
        List<Exercise> exercises = exerciseRepository.getAllExercises();
        //Then
        assertThat(exercises).isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(exercise1, exercise2);
    }

    @Test
    public void ExerciseRepository_GetAllExercises_ShouldReturnAnEmptyList() {
        //When
        List<Exercise> exercises = exerciseRepository.getAllExercises();
        //Then
        assertThat(exercises).isNotNull()
                .isEmpty();
    }

}