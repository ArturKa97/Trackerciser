package com.exercise.project.dtos;

import lombok.Builder;

@Builder
public record LoginAndRegisterRequest(
        String username,
        String password
) {

}
