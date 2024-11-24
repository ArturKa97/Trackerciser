package com.exercise.project.mappers;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserRoleDTOMapper.class})
public interface UserDTOMapper {

    @Mapping(target = "rolesDTO", source = "roles")
    UserDTO toDTO(User user);

}
