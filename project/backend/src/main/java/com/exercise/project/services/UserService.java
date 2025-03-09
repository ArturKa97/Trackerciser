package com.exercise.project.services;

import com.exercise.project.dtos.LoginAndRegisterRequest;
import com.exercise.project.dtos.UserDTO;

public interface UserService {

    void addNewUser(LoginAndRegisterRequest registerRequest);

    UserDTO getUserById(Long id);
}
