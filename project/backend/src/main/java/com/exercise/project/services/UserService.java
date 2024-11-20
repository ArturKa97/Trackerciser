package com.exercise.project.services;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;

public interface UserService {

    void addNewUser(User user);

    void addRoleToUser(Long userId, Long roleId);

    UserDTO getUserById(Long id);
}
