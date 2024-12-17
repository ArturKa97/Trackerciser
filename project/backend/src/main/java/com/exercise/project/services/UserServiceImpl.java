package com.exercise.project.services;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.mappers.UserDTOMapper;
import com.exercise.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOMapper userDTOMapper;
    private final RoleService roleService;

    public void addNewUser(User userToRegister) {
        User newUser = User.builder()
                .username(userToRegister.getUsername())
                .password(passwordEncoder.encode(userToRegister.getPassword()))
                .build();
        User savedUser = userRepository.save(newUser);

        roleService.addRoleToUser(savedUser.getId(), 2L); //id: 2 = USER, adding a USER role on a newly added user;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.getUserById(id).map(userDTOMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with id [%s] not found".formatted(id)));
    }

}
