package com.thinkconstructive.book_store.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    public String generateToken(String username);
    public String extractUsername(String token);
    boolean validateToken(String jwtToken, UserDetails userDetails);
}
