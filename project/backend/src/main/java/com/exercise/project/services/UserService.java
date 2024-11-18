package com.exercise.project.services;

import com.exercise.project.entities.User;

import java.util.Optional;

public interface UserService {

    void addNewUser(User user);

    Optional<User> getUserById(Long id);
}
