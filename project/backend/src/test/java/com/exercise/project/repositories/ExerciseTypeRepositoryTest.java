package com.exercise.project.repositories;

import com.exercise.project.entities.ExerciseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ExerciseTypeRepositoryTest {

    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;

    @Test
    public void ExerciseTypeRepository_GetExerciseTypeById_ShouldReturnExerciseTypeEntity() {
        //Given
        ExerciseType exerciseType = ExerciseType.builder()
                .name("Squats")
                .build();
        //When
        ExerciseType savedExerciseType = exerciseTypeRepository.save(exerciseType);
        ExerciseType fetchedExerciseType = exerciseTypeRepository.getExerciseTypeById(savedExerciseType.getId()).get();
        //Then
        assertThat(fetchedExerciseType).isNotNull();
        assertThat(fetchedExerciseType).isEqualTo(savedExerciseType);
    }

}