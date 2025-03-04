package com.domain.blog.service;

import com.domain.blog.entity.Permission;
import com.domain.blog.entity.User;
import com.domain.blog.dto.request.LoginSocialMediaRequest;
import com.domain.blog.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface UserService {


    UserResponse convertUserToUserDTO(User user);

    User createUser(User user);

    User getUserById(String id);

    UserResponse getUserResponseById(String id);

    User getUserByUsername(String username);

    UserResponse updateUser(MultipartFile file, String id, String name, String image) throws URISyntaxException, IOException;

    void updateUserRefreshToken(String refreshToken, String username);

    User getUserByRefreshTokenAndUsername(String token, String username);

    boolean existsById(String id);

    User updateUserSocialMedia(User userDB, LoginSocialMediaRequest loginSocialMediaRequest);

    List<Permission> getPermissionFromUserRole(String username);

    UserResponse getUserResponseByUsername(String username);

    UserResponse convertUserResponse(User user);
}