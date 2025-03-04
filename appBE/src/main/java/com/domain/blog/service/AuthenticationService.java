package com.domain.blog.service;

import com.domain.blog.dto.request.LoginRequest;
import com.domain.blog.dto.request.LoginSocialMediaRequest;
import com.domain.blog.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface AuthenticationService {

    LoginResponse handleLogin(LoginRequest loginRequest);

    LoginResponse handleRefreshToken(String refreshToken);

    LoginResponse handleLoginWithSocialMedia(LoginSocialMediaRequest loginSocialMediaRequest);
}