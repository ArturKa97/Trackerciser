package com.exercise.project.controllers;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.services.RoleService;
import com.exercise.project.services.RoleServiceImpl;
import com.exercise.project.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @PostMapping
    public void addNewUser(@Valid @RequestBody User user) {
        userService.addNewUser(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{userId}/{roleId}")
    public void addRoleToUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) {
        roleService.addRoleToUser(userId, roleId);
    }
}
