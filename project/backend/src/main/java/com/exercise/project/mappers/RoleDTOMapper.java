package com.exercise.project.mappers;

import com.exercise.project.dtos.RoleDTO;
import com.exercise.project.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleDTOMapper {

    @Mapping(target = "roleDTO", source = "role.role")
    RoleDTO toDTO(Role role);

}
