package com.exercise.project.dtos;

import lombok.Builder;

@Builder
public record UserDTO(Long id,
                      String username) {

}
