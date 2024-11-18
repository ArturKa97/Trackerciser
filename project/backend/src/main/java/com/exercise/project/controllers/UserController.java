package com.exercise.project.controllers;

import com.exercise.project.entities.User;
import com.exercise.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById (@PathVariable (value = "id") Long id) {
        return userService.getUserById(id);
    }

}
