package com.exercise.project.controllers;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.services.RoleService;
import com.exercise.project.services.RoleServiceImpl;
import com.exercise.project.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User's Registration And Role Managing")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Operation(description = "Post endpoint for the new User registration",
    summary = "Register the new user")
    @PostMapping("/register")
    public void addNewUser(@Valid @RequestBody User user) {
        userService.addNewUser(user);
    }

    @Operation(description = "Get endpoint for retrieving the User",
    summary = "Used for internal operations for user fetching, and for testing in the development")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @Operation(description = "Post endpoint for User's Role assignment",
    summary = "Manual role assignment for user, will be hidden further in the development when ADMIN role will be required for some operations")
    @PostMapping("/{userId}/{roleId}")
    public void addRoleToUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) {
        roleService.addRoleToUser(userId, roleId);
    }
}
