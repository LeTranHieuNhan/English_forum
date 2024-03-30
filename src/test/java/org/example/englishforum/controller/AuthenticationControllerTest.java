package org.example.englishforum.controller;

import org.example.englishforum.dto.UserDto;
import org.example.englishforum.service.AuthenticationService;
import org.example.englishforum.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    AuthenticationService authService;

    @Mock
    JwtService jwtService;

    @InjectMocks
    AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should login user and return ok status")
    void loginUser() {
        // Create a userDto
        UserDto userDto = new UserDto();

        // Mock the behavior of the signIn method in the authService
        when(authService.signIn(userDto)).then(invocation -> {
            UserDto argument = invocation.getArgument(0);
            // Modify the userDto as per your service's behavior
            argument.setUsername("John");
            argument.setPassword("Doe");
            return argument; // Return the modified userDto
        });

        // Call the login method in the authenticationController
        ResponseEntity<?> response = authenticationController.login(userDto);

        // Assert the expected result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody()); // This might need to be adjusted based on your service's behavior
    }

    @Test
    @DisplayName("Should register user and return created status")
    void registerUser() {
        UserDto userDto = new UserDto();
        when(authService.signUp(userDto)).then(invocation -> {
            UserDto argument = invocation.getArgument(0);
            // Modify the userDto as per your service's behavior
            argument.setEmail("Jane");
            argument.setPassword("Doe");
            return null;
        });

        ResponseEntity<?> response = authenticationController.register(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    @DisplayName("Should get user by token and return ok status")
    void getUserByToken() {
        UserDto userDto = new UserDto();
        String token = "token";
        when(jwtService.findUserByToken(token)).thenReturn(userDto);

        ResponseEntity<?> response = authenticationController.getUser(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }
}
