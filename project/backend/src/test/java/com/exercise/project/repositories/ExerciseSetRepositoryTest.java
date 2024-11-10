package com.exercise.project.repositories;

import com.exercise.project.entities.ExerciseSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ExerciseSetRepositoryTest {

    @Autowired
    ExerciseSetRepository exerciseSetRepository;

    @Test
    public void ExerciseSetRepository_GetExerciseSetById_ShouldReturnExerciseSetEntity() {
        //Arrange
        ExerciseSet exerciseSet = ExerciseSet.builder()
                .sets(1L)
                .reps(10L)
                .weight(100F)
                .rest(90L).
                build();
        //Act
        ExerciseSet savedExerciseSet = exerciseSetRepository.save(exerciseSet);
        ExerciseSet fetchedExerciseSet = exerciseSetRepository.getExerciseSetById(savedExerciseSet.getId()).get();
        //Assert
        assertThat(fetchedExerciseSet).isNotNull();
        assertThat(fetchedExerciseSet.getId()).isEqualTo(savedExerciseSet.getId());
    }

    @Test
    public void ExerciseSetRepository_GetExerciseSetById_ShouldReturnEmptyOptionalWhenExerciseSetDoesNotExist() {
        //Arrange
        Long nonExistentId = 999999L;
        //Act
        Optional<ExerciseSet> fetchedExerciseSet = exerciseSetRepository.getExerciseSetById(nonExistentId);
        //Assert
        assertThat(fetchedExerciseSet).isEmpty();

    }

}