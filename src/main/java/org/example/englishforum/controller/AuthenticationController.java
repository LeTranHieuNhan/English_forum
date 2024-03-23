package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.service.AuthenticationService;
import org.example.englishforum.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto requestDto) {

        return new ResponseEntity<>(authService.signIn(requestDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        return new ResponseEntity<>(authService.signUp(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<UserDto> getUser(@PathVariable String token) {
        return new ResponseEntity<>(jwtService.findUserByToken(token),HttpStatus.OK);
    }
}
