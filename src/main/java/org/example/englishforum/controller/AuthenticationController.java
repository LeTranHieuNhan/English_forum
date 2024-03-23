package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto requestDto) {

        return new ResponseEntity<>(authService.signIn(requestDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(authService.signUp(userDto), HttpStatus.CREATED);
    }
}
