package com.exercise.project.security;

import com.exercise.project.dtos.UserDTO;

public record AuthenticationResponse(String token,
                                     UserDTO userDTO) {

}
