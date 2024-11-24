package com.exercise.project.mappers;

import com.exercise.project.dtos.UserRoleDTO;
import com.exercise.project.entities.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleDTOMapper.class})
public interface UserRoleDTOMapper {

    @Mapping(target = "roleDTO", source = "userRole.role")
    UserRoleDTO toDTO(UserRole userRole);

}
