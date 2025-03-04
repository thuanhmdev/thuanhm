package com.domain.blog.service.Impl;

import com.domain.blog.entity.User;
import com.domain.blog.dto.request.LoginRequest;
import com.domain.blog.dto.request.LoginSocialMediaRequest;
import com.domain.blog.dto.response.LoginResponse;
import com.domain.blog.dto.response.UserResponse;
import com.domain.blog.service.AuthenticationService;
import com.domain.blog.service.RoleService;
import com.domain.blog.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUTH-SERVICE")
@Tag(name = "AUTH-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${app.jwt.jwt-validity-in-hours}")
    private long jwtExpiration;
    @Value("${app.jwt.refresh-token-validity-in-days}")
    private long refreshTokenExpiration;

    @Value("${app.jwt.jwt-secret-key}")
    private String jwtSecretKey;

    private final JwtServiceImpl jwtService;
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse handleLogin(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        // Authenticate in loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserResponse userResponse = userService.getUserResponseByUsername(loginRequest.getUsername());

        // create access_token
        String accessToken = jwtService.createToken(userResponse, jwtEncoder, jwtExpiration);

        //Create refresh token, update refresh token for user and setCookie
        String refreshToken = jwtService.createRefreshToken(loginRequest.getUsername(), userResponse, jwtEncoder, refreshTokenExpiration);

        userService.updateUserRefreshToken(refreshToken, loginRequest.getUsername());

        return LoginResponse
                .builder().accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userResponse)
                .build();
    }

    public LoginResponse handleRefreshToken(String refreshToken) {
        try {
            if (Objects.equals(refreshToken, "")) {
                throw new BadCredentialsException("Invalid Refresh token");
            }
            //check valid token
            Jwt decodedToken = jwtService.checkValidRefreshToken(refreshToken, jwtSecretKey);
            String username = decodedToken.getSubject();

            // check user by token + email
            User currentUserDB = userService.getUserByRefreshTokenAndUsername(refreshToken, username);
            if (currentUserDB == null) {
                throw new BadCredentialsException("Refresh Token is invalid");
            }

            UserResponse userResponse = userService.convertUserResponse(currentUserDB);
            String createNewAccessToken = jwtService.createToken(userResponse, jwtEncoder, jwtExpiration);

            return LoginResponse
                    .builder().accessToken(createNewAccessToken)
                    .refreshToken(refreshToken)
                    .user(userResponse)
                    .build();
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid refresh token");
        }
    }

    public LoginResponse handleLoginWithSocialMedia(LoginSocialMediaRequest loginSocialMediaRequest) {
        User userWithSocialMedia;
        Optional<User> userOptional = Optional.ofNullable(userService.getUserByUsername(loginSocialMediaRequest.getUsername()));
        if (userOptional.isPresent()) {
            userWithSocialMedia = userService.updateUserSocialMedia(userOptional.get(), loginSocialMediaRequest);
        } else {
            User newUser = new User();
            newUser.setName(loginSocialMediaRequest.getName());
            newUser.setUsername(loginSocialMediaRequest.getUsername());
            newUser.setProvider(loginSocialMediaRequest.getProvider());
            newUser.setRole(roleService.findByName("USER"));
            newUser.setPassword(String.valueOf(System.currentTimeMillis()));
            newUser.setImageProvider(loginSocialMediaRequest.getImageProvider());
            userWithSocialMedia = userService.createUser(newUser);
        }
        LoginResponse loginResponse = new LoginResponse();
        UserResponse userDTO = userService.convertUserToUserDTO(userWithSocialMedia);
        loginResponse.setUser(userDTO);

        // create access_token
        String access_token = jwtService.createToken(userDTO, jwtEncoder, jwtExpiration);
        loginResponse.setAccessToken(access_token);

        //Create refresh token, update refresh token for user
        String refreshToken = jwtService.createRefreshToken(userWithSocialMedia.getUsername(), userDTO, jwtEncoder, refreshTokenExpiration);
        loginResponse.setRefreshToken(refreshToken);
        userService.updateUserRefreshToken(refreshToken, userWithSocialMedia.getUsername());
        return loginResponse;
    }

}
