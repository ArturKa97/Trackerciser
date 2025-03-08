package com.exercise.project.controllers;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.services.RoleService;
import com.exercise.project.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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

    @SecurityRequirements
    @Operation(description = "Post endpoint for the new User registration",
            summary = "Register the new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registers a new user and assigns a USER role",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed validation, incorrect provided values or username already taken", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/register") //TODO Change RequestBody to UserDTO
    public void addNewUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New user to be registered", required = true, content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class))) @Valid @RequestBody User user) {
        userService.addNewUser(user);
    }

    @Operation(description = "Get endpoint for retrieving the User",
            summary = "Used for internal operations for user fetching, and for testing in the development")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returns a selected user's dto",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User was not found with provided Id", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @GetMapping("/{id}")
    public UserDTO getUserById(@Parameter(in = ParameterIn.PATH, description = "Id of the user to be received", required = true)
                               @PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @Operation(description = "Post endpoint for User's Role assignment",
            summary = "Manual role assignment for user, will be hidden further in the development when ADMIN role will be required for some operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully assigns a role to the selected user",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized, JWT token invalid or expired", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or role was not found with provided Id's", content = @Content),
            @ApiResponse(responseCode = "500", description = "Bad request, default response code for when something bad unexpected happens", content = @Content)
    })
    @PostMapping("/{userId}/{roleId}")
    public void addRoleToUser(@Parameter(in = ParameterIn.PATH, description = "Id of the user for whom the role will be assigned to", required = true)
                              @PathVariable(value = "userId") Long userId,
                              @Parameter(in = ParameterIn.PATH, description = "Id of the role to be assigned to the user", required = true)
                              @PathVariable(value = "roleId") Long roleId) {
        roleService.addRoleToUser(userId, roleId);
    }
}
