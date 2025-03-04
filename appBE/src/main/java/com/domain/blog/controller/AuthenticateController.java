package com.domain.blog.controller;

import com.domain.blog.dto.request.LoginRequest;
import com.domain.blog.dto.request.LoginSocialMediaRequest;
import com.domain.blog.dto.response.LoginResponse;
import com.domain.blog.dto.response.PageResponse;
import com.domain.blog.dto.response.UserResponse;
import com.domain.blog.service.AuthenticationService;
import com.domain.blog.service.JwtService;
import com.domain.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog-api")
@RequiredArgsConstructor
@Tag(name = "AUTHENTICATION-CONTROLLER")
@Slf4j(topic = "AUTHENTICATION-CONTROLLER")
public class AuthenticateController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/auth/admin/login")
    public ResponseEntity<PageResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(
                PageResponse
                        .<LoginResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Đăng nhập thành công")
                        .data(authenticationService.handleLogin(loginRequest))
                        .build()
        );
    }

    @Operation(summary = "Get detail user", description = "API get detail user")
    @GetMapping("/auth/account")
    public ResponseEntity<PageResponse<UserResponse>> getAccount() {
        log.info("Fetch account");
        String username = jwtService.getCurrentUserLogin().isPresent() ? jwtService.getCurrentUserLogin().get() : null;
        return ResponseEntity.ok(
                PageResponse
                        .<UserResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy người dùng thành công")
                        .data(userService.getUserResponseByUsername(username))
                        .build()
        );
    }

    @Operation(summary = "Refresh token", description = "API refresh token")
    @PostMapping("/auth/refresh")
    public ResponseEntity<PageResponse<LoginResponse>> getRefreshToken(String refreshToken) {
        try{
            LoginResponse loginResponse = authenticationService.handleRefreshToken(refreshToken);
            return ResponseEntity.ok(
                    PageResponse
                            .<LoginResponse>builder()
                            .status(HttpStatus.OK.value())
                            .message("Lấy người dùng thành công")
                            .data(loginResponse)
                            .build()
            );
        }catch (Exception e){
            throw new BadCredentialsException("Invalid refresh token");
        }

    }

    @Operation(summary = "Logout", description = "API logout user")
    @PostMapping("/auth/logout")
    public ResponseEntity<PageResponse<Void>> logout()  {
        log.info("logout");
        String username = jwtService.getCurrentUserLogin().isPresent() ? jwtService.getCurrentUserLogin().get() : null;
        userService.updateUserRefreshToken("", username);
        return ResponseEntity.ok(
                PageResponse
                        .<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Đăng xuất thành công")
                        .data(null)
                        .build()
        );
    }

    @Operation(summary = "Login by social-media", description = "API login by social media (google)")
    @PostMapping("/auth/login-social-media")
    public ResponseEntity<PageResponse<LoginResponse>> loginWidthSocialMedia(@RequestBody LoginSocialMediaRequest loginSocialMediaRequest) {
        LoginResponse loginResponse = authenticationService.handleLoginWithSocialMedia(loginSocialMediaRequest);
        return ResponseEntity.ok(
                PageResponse
                        .<LoginResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Đăng nhập thành công")
                        .data(loginResponse)
                        .build()
        );
    }

}
