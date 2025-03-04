package com.domain.blog.service.Impl;
import com.domain.blog.constant.ProviderEnum;
import com.domain.blog.entity.Permission;
import com.domain.blog.entity.User;
import com.domain.blog.dto.request.LoginSocialMediaRequest;
import com.domain.blog.dto.response.RoleResponse;
import com.domain.blog.dto.response.UserResponse;
import com.domain.blog.repository.UserRepository;
import com.domain.blog.service.RoleService;
import com.domain.blog.service.UserService;
import com.domain.blog.util.FileUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.domain.blog.constant.FileConstant.allowedFileExtension;
import static com.domain.blog.constant.FileConstant.baseURI;

@Service
@RequiredArgsConstructor
@Slf4j(topic="USER-SERVICE")
@Tag(name="USER-SERVICE")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @DependsOn("RoleService")
    @PostConstruct
    public void initializeSettings() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("admin");
            user.setName("ADMIN");
            user.setImage("/avatar/default_user.png");
            user.setPassword(passwordEncoder.encode("admin121212"));
            user.setRole(roleService.findByName("ADMIN"));
            user.setProvider(ProviderEnum.SYSTEM);

            userRepository.save(user);
        }
    }

    public UserResponse convertUserToUserDTO(User user) {
        UserResponse userDTO = new UserResponse();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setImage(user.getImage());
        userDTO.setImageProvider(user.getImageProvider());
        userDTO.setRole(RoleResponse.builder().id(user.getRole().getId()).name(user.getRole().getName()).build());
        userDTO.setProvider(user.getProvider());

        return userDTO;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }



    public User getUserById(String id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.orElse(null);
    }

    public UserResponse getUserResponseById(String id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.map(this::convertUserResponse).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public UserResponse updateUser(MultipartFile file, String id, String name, String image) throws URISyntaxException, IOException {
        User userUpdate = getUserById(id);
        if (userUpdate == null) {
            return null;
        }
        userUpdate.setImage(image);
        userUpdate.setName(name);

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            boolean isValid = allowedFileExtension.stream().anyMatch(item -> fileName.toLowerCase().endsWith(item));
            if (isValid) {

                // Delete old file if exists
                if(userUpdate.getImage() != null && !userUpdate.getImage().isEmpty()){
                    FileUtil.deleteOldFile(fileName, "avatar");
                }

                //Create a directory if not Exist
                FileUtil.createDirectory(baseURI + "avatar");
                //Store file and save name to DB
                String fileNameFinal = FileUtil.storeFile(file, "avatar");
                String HostAndPort= ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                userUpdate.setImage( "/" +"avatar" + "/" + fileNameFinal);
            }
        } else {
            if (Objects.equals(userUpdate.getImage(), "") || userUpdate.getImage() == null) {
                // Delete old file if exists
                if(userUpdate.getImage() != null && !userUpdate.getImage().isEmpty()){
                    FileUtil.deleteOldFile(userUpdate.getImage(), "avatar");
                }
                userUpdate.setImage(null);
            } else {
                userUpdate.setImage(userUpdate.getImage());
            }
        }
        return convertUserResponse(userRepository.save(userUpdate));

    }

    public void updateUserRefreshToken(String refreshToken, String username) {
        User user = userRepository.findByUsername(username);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }

    public User getUserByRefreshTokenAndUsername(String token, String username) {
        return userRepository.findByRefreshTokenAndUsername(token, username);
    }

    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }

    public User updateUserSocialMedia(User userDB, LoginSocialMediaRequest loginSocialMediaRequest) {
        userDB.setName(loginSocialMediaRequest.getName());
        userDB.setImageProvider(loginSocialMediaRequest.getImageProvider());

        return userRepository.save(userDB);
    }


    public List<Permission> getPermissionFromUserRole(String username){
        User user = userRepository.findByUsername(username);
        return user.getRole().getPermissions();

    }

    public UserResponse getUserResponseByUsername(String username) {
        return convertUserResponse(userRepository.findByUsername(username));
    }

    public UserResponse convertUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .image(user.getImage())
                .imageProvider(user.getImageProvider())
                .role(RoleResponse.builder()
                        .id(user.getRole().getId())
                        .name(user.getRole().getName())
                        .build())
                .provider(user.getProvider())
                .build();
    }

}
