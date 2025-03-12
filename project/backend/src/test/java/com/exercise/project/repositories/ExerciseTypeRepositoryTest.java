package com.exercise.project.repositories;

import com.exercise.project.entities.ExerciseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class ExerciseTypeRepositoryTest {

    private final Long NONEXISTENTID = 999999L;
    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;
    private ExerciseType createExerciseTypeTestEntity(String name) {
        return ExerciseType.builder()
                .name(name)
                .build();
    }

    @Test
    public void ExerciseTypeRepository_GetExerciseTypeById_ShouldReturnExerciseTypeEntity() {
        //Given
        ExerciseType exerciseType = createExerciseTypeTestEntity("Squats");
        //When
        ExerciseType savedExerciseType = exerciseTypeRepository.save(exerciseType);
        ExerciseType fetchedExerciseType = exerciseTypeRepository.getExerciseTypeById(savedExerciseType.getId()).get();
        //Then
        assertThat(fetchedExerciseType).isNotNull()
                .isEqualTo(savedExerciseType);
    }

    @Test
    public void ExerciseTypeRepository_GetExerciseTypeById_ShouldReturnOptionalWhenExerciseTypeDoesNotExist() {
        //When
        Optional<ExerciseType> fetchedExerciseType = exerciseTypeRepository.getExerciseTypeById(NONEXISTENTID);
        //Then
        assertThat(fetchedExerciseType).isNotNull()
                .isEmpty();

    }

    @Test
    public void ExerciseTypeRepository_GetAllExerciseTypes_ShouldReturnAnExerciseTypeList() {
        //Given
        ExerciseType exerciseType1 = createExerciseTypeTestEntity("Bench Press");
        ExerciseType exerciseType2 = createExerciseTypeTestEntity("Curls");
        //When
        exerciseTypeRepository.save(exerciseType1);
        exerciseTypeRepository.save(exerciseType2);
        List<ExerciseType> exerciseTypes = exerciseTypeRepository.getAllExerciseTypes();
        //Then
        assertThat(exerciseTypes).isNotNull()
                .hasSize(2)
                .containsExactlyInAnyOrder(exerciseType1, exerciseType2);

    }

    @Test
    public void ExerciseTypeRepository_GetAllExerciseTypes_ShouldReturnAnEmptyList() {
        //When
        List<ExerciseType> exerciseTypes = exerciseTypeRepository.getAllExerciseTypes();
        //Then
        assertThat(exerciseTypes).isNotNull()
                .isEmpty();
    }
}