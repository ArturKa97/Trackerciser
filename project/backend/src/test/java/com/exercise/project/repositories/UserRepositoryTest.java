package com.exercise.project.repositories;

import com.exercise.project.entities.User;
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
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_FindByUsername_ShouldReturnUserEntity() {
        //Given
        User user = User.builder()
                .username("user")
                .password("UserUser97!")
                .build();
        //When
        User savedUser = userRepository.save(user);
        User fetchedUser = userRepository.findByUsername(savedUser.getUsername()).get();
        //Then
        assertThat(fetchedUser).isNotNull()
                .isEqualTo(savedUser);

    }

    @Test
    public void UserRepository_FindByUsername_ShouldReturnOptionalEmptyWhenUserDoesNotExist() {
        //Given
        String nonExistentUsername = "nonExistent";
        //When
        Optional<User> fetchedUser = userRepository.findByUsername(nonExistentUsername);
        //Then
        assertThat(fetchedUser).isNotNull();
    }

    @Test
    public void UserRepository_GetUserById_ShouldReturnUserEntity() {
        //Given
        User user = User.builder()
                .username("user")
                .password("UserUser97!")
                .build();
        //When
        User savedUser = userRepository.save(user);
        User fetchedUser = userRepository.getUserById(savedUser.getId()).get();

        //Then
        assertThat(fetchedUser).isNotNull()
                .isEqualTo(savedUser);

    }

    @Test
    public void UserRepository_GetUserById_ShouldReturnOptionalEmptyWhenUserDoesNotExist() {
        //Given
        Long nonExistentId = 999999L;
        //When
        Optional<User> fetchedUser = userRepository.getUserById(nonExistentId);
        //Then
        assertThat(fetchedUser).isNotNull()
                .isEmpty();
    }
}