package com.exercise.project.services;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;

public interface UserService {

    void addNewUser(User user);

    UserDTO getUserById(Long id);
}
