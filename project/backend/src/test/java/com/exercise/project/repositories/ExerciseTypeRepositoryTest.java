package com.exercise.project.repositories;

import com.exercise.project.entities.ExerciseType;
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

    @Test
    public void ExerciseTypeRepository_GetExerciseTypeById_ShouldReturnOptionalWhenExerciseTypeDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When
        Optional<ExerciseType> fetchedExerciseType = exerciseTypeRepository.getExerciseTypeById(nonExistentId);
        //Then
        assertThat(fetchedExerciseType).isNotNull();
        assertThat(fetchedExerciseType).isEmpty();

    }

    @Test
    public void ExerciseTypeRepository_GetAllExerciseTypes_ShouldReturnAnExerciseTypeList() {
        //Given
        ExerciseType exerciseType1 = ExerciseType.builder()
                .name("Bench Press")
                .build();
        ExerciseType exerciseType2 = ExerciseType.builder()
                .name("Curls")
                .build();
        //When
        exerciseTypeRepository.save(exerciseType1);
        exerciseTypeRepository.save(exerciseType2);
        List<ExerciseType> exerciseTypes = exerciseTypeRepository.getAllExerciseTypes();
        //Then
        assertThat(exerciseTypes).isNotNull();
        assertThat(exerciseTypes).hasSize(2);
        assertThat(exerciseTypes).containsExactlyInAnyOrder(exerciseType1, exerciseType2);

    }

    @Test
    public void ExerciseTypeRepository_GetAllExerciseTypes_ShouldReturnAnEmptyList() {
        //When
        List<ExerciseType> exerciseTypes = exerciseTypeRepository.getAllExerciseTypes();
        //Then
        assertThat(exerciseTypes).isNotNull();
        assertThat(exerciseTypes).isEmpty();
    }
}