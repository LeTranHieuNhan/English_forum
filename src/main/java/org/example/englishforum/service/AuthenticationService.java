package org.example.englishforum.service;

import org.example.englishforum.dto.SigninRequest;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.dto.response.JwtResponse;

public interface AuthenticationService {
    JwtResponse signUp(UserDto signUpRequest);
    JwtResponse signIn(UserDto userDto);
}
