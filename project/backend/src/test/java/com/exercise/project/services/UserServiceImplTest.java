package com.exercise.project.services;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.mappers.UserDTOMapper;
import com.exercise.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

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

    @Test
    public void UserServiceImpl_AddNewUser_ShouldAddANewUserWithRole() {
        //Given
        User userToRegister = User.builder()
                .username("user")
                .password("rawPassword")
                .build();

        String encodedPassword = "encodedPassword";

        User newUser = User.builder()
                .username(userToRegister.getUsername())
                .password(encodedPassword)
                .build();

        User savedUser = User.builder()
                .id(1L)
                .username("user")
                .password(encodedPassword)
                .build();

        when(passwordEncoder.encode(userToRegister.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(newUser)).thenReturn(savedUser);

        //When
        userService.addNewUser(userToRegister);

        //Then
        verify(passwordEncoder).encode(userToRegister.getPassword());
        verify(userRepository).save(userToRegister);
        verify(roleService).addRoleToUser(savedUser.getId(), 2L);
        assertThat(savedUser).isNotSameAs(userToRegister);
        assertThat(savedUser.getPassword())
                .isNotEqualTo(userToRegister.getPassword())
                .isEqualTo(encodedPassword);

    }

    @Test
    public void UserServiceImpl_GetUserById_ShouldReturnAnUserDTO() {
        //Given
        Long userId = 1L;
        User fetchedUser = User.builder()
                .username("user")
                .password("encodedPassword")
                .roles(Set.of())
                .build();

        UserDTO returnedUserDTO = UserDTO.builder()
                .username("user")
                .rolesDTO(Set.of())
                .build();

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
        Long nonExistentId = 999999L;
        when(userRepository.getUserById(nonExistentId)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(nonExistentId);
        });
        assertEquals("User with id [999999] not found", exception.getMessage());
    }
}