package com.exercise.project.mappers;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    UserDTO toDTO(User user);

}
