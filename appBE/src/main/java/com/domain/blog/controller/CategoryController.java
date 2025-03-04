package com.domain.blog.controller;

import com.domain.blog.dto.request.CommentRequest;
import com.domain.blog.dto.response.CategoryResponse;
import com.domain.blog.dto.response.CommentResponse;
import com.domain.blog.dto.response.PageResponse;
import com.domain.blog.service.CategoryService;
import com.domain.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog-api/category")
@RequiredArgsConstructor
@Slf4j(topic="CATEGORY-CONTROLLER")
@Tag(name="CATEGORY-CONTROLLER")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get full category list", description = "API get category list")
    @GetMapping("/list")
    public ResponseEntity<PageResponse<List<CategoryResponse>>>  getComments() {
        log.info("Get category list");
        return ResponseEntity.ok(
                PageResponse
                        .<List<CategoryResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách danh mục thành công")
                        .data(categoryService.getCategories())
                        .build()
        );
    }


}
