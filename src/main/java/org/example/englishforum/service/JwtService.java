package org.example.englishforum.service;

import org.example.englishforum.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(User userDetails);
    String extractUsername(String token);
    boolean isTokenValid(String token, User userDetails);

    String generateRefreshToken(Map<String,Object> extractClaims, User userDetails);
}
