package com.domain.blog.service;

import com.domain.blog.dto.response.UserResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.Optional;

public interface JwtService {

    String createToken(UserResponse userDTO, JwtEncoder jwtEncoder, long jwtExpiration);

    String createRefreshToken(String username, UserResponse userDTO, JwtEncoder jwtEncoder, long refreshTokenExpiration);

    Optional<String> getCurrentUserLogin();

    Jwt checkValidRefreshToken(String token, String jwtSecretKey);
}