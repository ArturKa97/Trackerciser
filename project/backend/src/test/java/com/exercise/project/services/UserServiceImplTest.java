package com.exercise.project.services;

import com.exercise.project.dtos.LoginAndRegisterRequest;
import com.exercise.project.dtos.UserDTO;
import com.exercise.project.dtos.UserRoleDTO;
import com.exercise.project.entities.User;
import com.exercise.project.entities.UserRole;
import com.exercise.project.mappers.UserDTOMapper;
import com.exercise.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    private final Long NONEXISTENTID = 999999L;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    private User createTestUserEntity(Long id, String username, String password, Set<UserRole> roles) {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }
    private UserDTO createTestUserDTO(Long id, String username, Set<UserRoleDTO> roles) {
        return UserDTO.builder()
                .id(id)
                .username(username)
                .rolesDTO(roles)
                .build();
    }

    @Test
    public void UserServiceImpl_AddNewUser_ShouldAddANewUserWithRole() {
        //Given
        LoginAndRegisterRequest registerRequest = LoginAndRegisterRequest.builder()
                .username("user")
                .password("rawPassword")
                .build();
        String encodedPassword = "encodedPassword";
        User newUser = createTestUserEntity(null, "user", encodedPassword, Set.of());
        User savedUser = createTestUserEntity(1L, "user", encodedPassword, Set.of());

        when(passwordEncoder.encode(registerRequest.password())).thenReturn(encodedPassword);
        when(userRepository.save(newUser)).thenReturn(savedUser);
        //When
        userService.addNewUser(registerRequest);

        //Then
        verify(passwordEncoder).encode(registerRequest.password());
        verify(userRepository).save(newUser);
        verify(roleService).addRoleToUser(savedUser.getId(), 2L);
        assertThat(savedUser).isNotSameAs(newUser);
        assertThat(savedUser.getPassword())
                .isNotEqualTo(registerRequest.password())
                .isEqualTo(encodedPassword);

    }

    @Test
    public void UserServiceImpl_AddNewUser_ShouldReturnIllegalArgumentExceptionWhenUserAlreadyExists() {
        //Given
        LoginAndRegisterRequest registerRequest = LoginAndRegisterRequest.builder()
                .username("user")
                .password("rawPassword")
                .build();
        String encodedPassword = "encodedPassword";
        User existingUser = createTestUserEntity(null, "user", encodedPassword, Set.of());
        when(userRepository.findByUsername(registerRequest.username())).thenReturn(Optional.of(existingUser));
        //When + Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.addNewUser(registerRequest);
        });
        assertEquals("Username is already taken.", exception.getMessage());
    }

    @Test
    public void UserServiceImpl_GetUserById_ShouldReturnAnUserDTO() {
        //Given
        Long userId = 1L;
        User fetchedUser = createTestUserEntity(1L, "user", "encodedPassword", Set.of());
        UserDTO returnedUserDTO = createTestUserDTO(1L, "user", Set.of());

        when(userRepository.getUserById(userId)).thenReturn(Optional.of(fetchedUser));
        when(userDTOMapper.toDTO(fetchedUser)).thenReturn(returnedUserDTO);
        //When
        UserDTO result = userService.getUserById(userId);

        //Then
        verify(userRepository).getUserById(userId);
        verify(userDTOMapper).toDTO(fetchedUser);
        assertThat(result).isNotNull().isEqualTo(returnedUserDTO);
    }
    @Test
    public void UserServiceImpl_GetUserById_ShouldThrowEntityNotFoundException() {
        //Given
        when(userRepository.getUserById(NONEXISTENTID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(NONEXISTENTID);
        });
        assertEquals("User with id [999999] not found", exception.getMessage());
    }
}