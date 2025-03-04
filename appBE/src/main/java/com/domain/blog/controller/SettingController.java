package com.domain.blog.controller;

import com.domain.blog.dto.request.SettingRequest;
import com.domain.blog.dto.response.ContactAdminResponse;
import com.domain.blog.dto.response.PageResponse;
import com.domain.blog.dto.response.SettingResponse;
import com.domain.blog.service.SettingService;
import com.domain.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Tag(name = "SETTING-CONTROLLER")
@Slf4j(topic = "SETTING-CONTROLLER")
@RequestMapping("/blog-api")
public class SettingController {
    private final SettingService settingService;

    @Operation(summary = "Get setting", description = "API get setting")
    @GetMapping("/setting")
    public ResponseEntity<PageResponse<SettingResponse>> getSetting() {
        return ResponseEntity.ok(
                PageResponse
                        .<SettingResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy thông tin thành công")
                        .data(settingService.getSettingResponse())
                        .build()
        );

    }

    @Operation(summary = "Get contact", description = "API get contact")
    @GetMapping("/contact")
    public ResponseEntity<PageResponse<ContactAdminResponse>> getInformationContactAdmin() {
        return ResponseEntity.ok(
                PageResponse
                        .<ContactAdminResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy thông tin thành công")
                        .data(settingService.getInformationContactAdmin())
                        .build()
        );
    }

    @Operation(summary = "Update setting", description = "API Update setting")
    @PutMapping("/setting")
    public ResponseEntity<PageResponse<SettingResponse>> updateSetting(@RequestBody SettingRequest setting) {
        SettingResponse settingUpdated = settingService.updateSetting(setting);

        return ResponseEntity.ok(
                PageResponse
                        .<SettingResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Cập nhật tin thành công")
                        .data(settingUpdated)
                        .build()
        );
    }

}
