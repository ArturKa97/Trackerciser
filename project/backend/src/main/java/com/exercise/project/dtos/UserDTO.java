package com.exercise.project.dtos;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserDTO(Long id,
                      String username,
                      Set<UserRoleDTO> rolesDTO) {

}
