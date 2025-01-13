package com.exercise.project.services;

import com.exercise.project.entities.Role;
import com.exercise.project.entities.User;
import com.exercise.project.entities.UserRole;
import com.exercise.project.entities.UserRoleId;
import com.exercise.project.repositories.RoleRepository;
import com.exercise.project.repositories.UserRepository;
import com.exercise.project.repositories.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    private final Long NONEXISTENTID = 999999L;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @InjectMocks
    private RoleServiceImpl roleServiceImpl;
    private User createTestUserEntity(Long id, String username, String password, Set<UserRole> roles) {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }

    @Test
    public void RoleServiceImpl_AddRoleToUser_ShouldAddARoleToAnUser() {
        //Given
        Long userId = 1L;
        Long roleId = 2L;

        User user = createTestUserEntity(userId, "user", "encodedPassword", Set.of());

        Role role = Role.builder()
                .id(roleId)
                .role("USER")
                .build();

        UserRoleId userRoleId = UserRoleId.builder()
                .userId(userId)
                .roleId(roleId)
                .build();

        UserRole userRole = UserRole.builder()
                .userRoleId(userRoleId)
                .user(user)
                .role(role)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        //When
        roleServiceImpl.addRoleToUser(userId, roleId);
        //Then
        verify(userRepository).findById(userId);
        verify(roleRepository).findById(roleId);
        verify(userRoleRepository).save(userRole);
    }

    @Test
    public void RoleServiceImpl_AddRoleToUser_ShouldThrowEntityNotFoundExceptionWhenUserDoesNotExist() {
        //Given
        Long roleId = 1L;
        when(userRepository.findById(NONEXISTENTID)).thenReturn(Optional.empty());
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleServiceImpl.addRoleToUser(NONEXISTENTID, roleId);
        });
        assertEquals("User with id [999999] not found", exception.getMessage());
    }

    @Test
    public void RoleServiceImpl_AddRoleToUser_ShouldThrowEntityNotFoundExceptionWhenRoleDoesNotExist() {
        //Given
        Long userId = 1L;
        User user = createTestUserEntity(userId, "user", "encodedPassword", Set.of());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        //When + Then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            roleServiceImpl.addRoleToUser(userId, NONEXISTENTID);
        });
        assertEquals("Role with id [999999] not found", exception.getMessage());
    }
}