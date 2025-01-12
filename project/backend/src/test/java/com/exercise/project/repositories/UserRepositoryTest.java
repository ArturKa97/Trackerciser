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

    private final String NONEXISTENTUSERNAME = "nonExistent";
    private final Long NONEXISTENTID = 999999L;
    @Autowired
    private UserRepository userRepository;
    private User createUserTestEntity(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
    @Test
    public void UserRepository_FindByUsername_ShouldReturnUserEntity() {
        //Given
        User user = createUserTestEntity("user", "UserUser97!");
        //When
        User savedUser = userRepository.save(user);
        User fetchedUser = userRepository.findByUsername(savedUser.getUsername()).get();
        //Then
        assertThat(fetchedUser).isNotNull()
                .isEqualTo(savedUser);

    }

    @Test
    public void UserRepository_FindByUsername_ShouldReturnOptionalEmptyWhenUserDoesNotExist() {
        //When
        Optional<User> fetchedUser = userRepository.findByUsername(NONEXISTENTUSERNAME);
        //Then
        assertThat(fetchedUser).isNotNull();
    }

    @Test
    public void UserRepository_GetUserById_ShouldReturnUserEntity() {
        //Given
        User user = createUserTestEntity("user", "UserUser97!");
        //When
        User savedUser = userRepository.save(user);
        User fetchedUser = userRepository.getUserById(savedUser.getId()).get();
        //Then
        assertThat(fetchedUser).isNotNull()
                .isEqualTo(savedUser);
    }

    @Test
    public void UserRepository_GetUserById_ShouldReturnOptionalEmptyWhenUserDoesNotExist() {
        //When
        Optional<User> fetchedUser = userRepository.getUserById(NONEXISTENTID);
        //Then
        assertThat(fetchedUser).isNotNull()
                .isEmpty();
    }
}