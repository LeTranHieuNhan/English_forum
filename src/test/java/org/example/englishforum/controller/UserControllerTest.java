package org.example.englishforum.controller;

import org.example.englishforum.dto.UserDto;
import org.example.englishforum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get all users and return ok status")
    void getAllUsers() {
        List<UserDto> userDtoList = Collections.singletonList(new UserDto());
        when(userService.findAllUsers()).thenReturn(userDtoList);

        List<UserDto> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, HttpStatus.OK);
        assertEquals(userDtoList, response);
    }

    @Test
    @DisplayName("Should get user by id and return ok status")
    void getUserById() {
        UserDto userDto = new UserDto();
        when(userService.findUserById(1L)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    @DisplayName("Should create user and return created status")
    void createUser() throws IOException {
        UserDto userDto = new UserDto();
        when(userService.createUser(userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    @DisplayName("Should update user and return ok status")
    void updateUser() {
        UserDto userDto = new UserDto();
        when(userService.updateUser(1L, userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.updateUser(1L, userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    @DisplayName("Should delete user and return no content status")
    void deleteUser() {
        doNothing().when(userService).deleteUser(1L);

        userController.deleteUser(1L);

        verify(userService).deleteUser(1L);
    }
}
