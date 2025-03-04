package com.domain.blog.controller;


import com.domain.blog.dto.response.PageResponse;
import com.domain.blog.dto.response.UserResponse;
import com.domain.blog.exception.DataNotFoundException;
import com.domain.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/user")
@Tag(name = "USER-CONTROLLER")
@Slf4j(topic = "USER-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get detail user", description = "API get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<PageResponse<UserResponse>>getUserById(@PathVariable("id") String id) {
        log.info("Get user by id");
        UserResponse userResponse = userService.getUserResponseById(id);

        return ResponseEntity.ok(
                PageResponse
                        .<UserResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy người dùng thành công")
                        .data(userResponse)
                        .build()
        );
    }

    @Operation(summary = "Update user information ", description = "API update user information")
    @PutMapping("/update")
    public ResponseEntity<PageResponse<UserResponse>> updateUser(@RequestParam(name = "file", required = false) MultipartFile file,
                                                                 @RequestParam(name = "id") String id,
                                                                 @RequestParam(name = "name") String name,
                                                                 @RequestParam(name = "image", required = false) String image
    ) throws DataNotFoundException, URISyntaxException, IOException {

        log.info("Update user");
        UserResponse userResponse = userService.updateUser(file, id, name, image);
        if (userResponse == null) {
            throw new DataNotFoundException("Không tìm thấy người dùng");
        }

        return ResponseEntity.ok(
                PageResponse
                        .<UserResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Cập nhật thành công")
                        .data(userResponse)
                        .build()
        );
    }
}
