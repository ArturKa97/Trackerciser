package com.exercise.project.controllers;

import com.exercise.project.dtos.UserDTO;
import com.exercise.project.entities.User;
import com.exercise.project.services.RoleService;
import com.exercise.project.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private RoleService roleService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void UserController_AddNewUser_ShouldReturnIsOk() throws Exception {
        //Given
        User user = User.builder()
                .id(1L)
                .username("user")
                .password("UserUser97!")
                .roles(new HashSet<>())
                .build();
        //When
        ResultActions response = mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        //Then
        response
                .andExpect(status().isOk());
        verify(userService).addNewUser(user);
    }

    @Test
    public void UserController_GetUserById_ShouldReturnUserDTO() throws Exception {
        //Given
        Long userId = 1L;
        UserDTO userDTO = UserDTO.builder()
                .id(userId)
                .username("user")
                .rolesDTO(new HashSet<>())
                .build();
        when(userService.getUserById(userId)).thenReturn(userDTO);
        //When
        ResultActions response = mockMvc.perform(get("/user/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDTO)));
        verify(userService).getUserById(userId);
    }

    @Test
    public void UserController_AddRoleToUser_ShouldReturnIsOk() throws Exception {
        //Given
        Long userId = 1L;
        Long roleId = 2L;
        //When
        ResultActions response = mockMvc.perform(post("/user/{userId}/{roleId}", userId, roleId)
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        response
                .andExpect(status().isOk());
        verify(roleService).addRoleToUser(userId, roleId);
    }

}