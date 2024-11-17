package com.exercise.project.dtos;

import lombok.Data;

public record LoginRequest(
        String username,
        String password
) {

}
