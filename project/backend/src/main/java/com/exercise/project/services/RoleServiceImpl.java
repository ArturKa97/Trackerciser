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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void addRoleToUser(Long userId, Long roleId) {

        User userToAddRole = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id [%s] not found".formatted(userId)));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role with id [%s] not found".formatted(roleId)));

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

}
