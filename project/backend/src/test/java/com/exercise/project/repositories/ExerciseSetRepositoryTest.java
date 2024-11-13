package com.exercise.project.repositories;

import com.exercise.project.entities.ExerciseSet;
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
class ExerciseSetRepositoryTest {

    @Autowired
    private ExerciseSetRepository exerciseSetRepository;

    @Test
    public void ExerciseSetRepository_GetExerciseSetById_ShouldReturnExerciseSetEntity() {
        //Given
        ExerciseSet exerciseSet = ExerciseSet.builder()
                .sets(1L)
                .reps(10L)
                .weight(100F)
                .rest(90L).
                build();
        //When
        ExerciseSet savedExerciseSet = exerciseSetRepository.save(exerciseSet);
        ExerciseSet fetchedExerciseSet = exerciseSetRepository.getExerciseSetById(savedExerciseSet.getId()).get();
        //Then
        assertThat(fetchedExerciseSet).isNotNull()
                .isEqualTo(savedExerciseSet);

    }

    @Test
    public void ExerciseSetRepository_GetExerciseSetById_ShouldReturnEmptyOptionalWhenExerciseSetDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When
        Optional<ExerciseSet> fetchedExerciseSet = exerciseSetRepository.getExerciseSetById(nonExistentId);
        //Then
        assertThat(fetchedExerciseSet).isNotNull()
                .isEmpty();

    }

}