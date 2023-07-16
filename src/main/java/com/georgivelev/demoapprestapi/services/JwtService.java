package com.georgivelev.demoapprestapi.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String generateToken(UserDetails userDetails, Map<String, Object> claims);

    String generateToken(UserDetails userDetails);

    String extractUsername(String jwtToken);

    Date extractExpirationDate(String jwtToken);

    Claims extractAllClaims(String jwtToken);

    <T> T extractClaims(String jwtToken, Function<Claims, T> keyExtractor);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    boolean isTokenExpired(String jwtToken);
}
