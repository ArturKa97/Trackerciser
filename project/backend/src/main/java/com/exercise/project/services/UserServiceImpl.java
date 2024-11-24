package com.exercise.project.services;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.Role;
import com.exercise.project.entities.User;
import com.exercise.project.entities.UserRole;
import com.exercise.project.entities.UserRoleId;
import com.exercise.project.mappers.UserDTOMapper;
import com.exercise.project.repositories.RoleRepository;
import com.exercise.project.repositories.UserRepository;
import com.exercise.project.repositories.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDTOMapper userDTOMapper;

    public void addNewUser(User userToRegister) {
        User newUser = User.builder()
                .username(userToRegister.getUsername())
                .password(passwordEncoder.encode(userToRegister.getPassword()))
                .build();
        User savedUser = userRepository.save(newUser);

        addRoleToUser(savedUser.getId(), 2L);
    }
    @Override
    public void addRoleToUser(Long userId, Long roleId) {

        User userToAddRole = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id [%s] not found".formatted(userId)));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("[%s] role not found".formatted(roleId)));

        UserRoleId userRoleId = UserRoleId.builder()
                .userId(userToAddRole.getId())
                .roleId(role.getId())
                .build();

        UserRole userRole = UserRole.builder()
                .userRoleId(userRoleId)
                .user(userToAddRole)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.getUserById(id).map(userDTOMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with id [%s] not found".formatted(id)));
    }

}
