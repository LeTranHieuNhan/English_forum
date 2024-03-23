package org.example.englishforum.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.dto.RoleDto;
import org.example.englishforum.dto.UserDto;
import org.example.englishforum.dto.response.JwtResponse;
import org.example.englishforum.entity.Role;
import org.example.englishforum.entity.User;
import org.example.englishforum.repository.RoleRepository;
import org.example.englishforum.repository.UserRepository;
import org.example.englishforum.service.AuthenticationService;
import org.example.englishforum.service.JwtService;
import org.example.englishforum.utils.GenericMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final GenericMapper genericMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    @Transactional
    public JwtResponse signUp(UserDto newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role does not exist"));
        User user = genericMapper.map(newUser, User.class);

        // Set the user's role
        user.setRole(role);

        User savedUser = userRepository.save(user);
        UserDto mappedUser = genericMapper.map(savedUser, UserDto.class);
        mappedUser.setRole(genericMapper.map(role, RoleDto.class));

        // Generate JWT token for the newly registered user
        String jwt = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), savedUser);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setRefreshToken(refreshToken);

        return jwtResponse;
    }

    @Override
    @Transactional
    public JwtResponse signIn(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        System.out.println("cook");
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found with email address" + userDto.getEmail()));
        // Generate JWT token for the signed-in user
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(jwt);
        jwtResponse.setRefreshToken(refreshToken);
        return jwtResponse;
    }
}
