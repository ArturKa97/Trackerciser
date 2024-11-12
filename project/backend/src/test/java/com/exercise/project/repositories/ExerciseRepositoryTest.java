package com.exercise.project.repositories;

import com.exercise.project.entities.Exercise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

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
        Exercise fetchedExercise = exerciseRepository.getExerciseById(exercise.getId()).get();
        //Then
        assertThat(fetchedExercise).isNotNull();
        assertThat(fetchedExercise).isEqualTo(savedExercise);
    }

    @Test
    public void ExerciseRepository_GetExerciseById_ShouldReturnAnOptionalWhenExerciseDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When

        Optional<Exercise> fetchedExercise = exerciseRepository.getExerciseById(nonExistentId);
        //Then
        assertThat(fetchedExercise).isEmpty();
    }

}