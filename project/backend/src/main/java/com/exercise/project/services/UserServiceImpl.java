package com.exercise.project.services;

import com.exercise.project.entities.Role;
import com.exercise.project.entities.User;
import com.exercise.project.entities.UserRole;
import com.exercise.project.entities.UserRoleId;
import com.exercise.project.repositories.RoleRepository;
import com.exercise.project.repositories.UserRepository;
import com.exercise.project.repositories.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public void addNewUser(User userToRegister) {
        User newUser = User.builder()
                .username(userToRegister.getUsername())
                .password(passwordEncoder.encode(userToRegister.getPassword()))
                .build();
        User savedUser = userRepository.save(newUser);

        Role role = roleRepository.findById("USER")
                .orElseThrow(() -> new EntityNotFoundException("[%s] role not found".formatted("USER")));

        UserRoleId userRoleId = UserRoleId.builder()
                .userId(savedUser.getId())
                .roleId(role.getRole())
                .build();

        UserRole userRole = UserRole.builder()
                .userRoleId(userRoleId)
                .user(savedUser)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
