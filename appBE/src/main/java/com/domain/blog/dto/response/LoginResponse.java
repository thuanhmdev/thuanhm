package com.domain.blog.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UserResponse user;
    private String refreshToken;
    private String accessToken;
}
