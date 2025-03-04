package com.domain.blog.controller;

import com.domain.blog.dto.request.CommentRequest;
import com.domain.blog.dto.response.CommentResponse;
import com.domain.blog.dto.response.PageResponse;
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
@RequestMapping("/blog-api/comment")
@RequiredArgsConstructor
@Slf4j(topic="COMMENT-CONTROLLER")
@Tag(name="COMMENT-CONTROLLER")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Get full comment list", description = "API get comment list")
    @GetMapping("/list")
    public ResponseEntity<PageResponse<List<CommentResponse>>>  getComments() {
        log.info("Get comment list");
        return ResponseEntity.ok(
                PageResponse
                        .<List<CommentResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách bình luận thành công")
                        .data(commentService.getComments())
                        .build()
        );
    }

    @Operation(summary = "Get comments by blog detail", description = "API get comments by blog detail")
    @GetMapping("/{blogId}")
    public ResponseEntity<PageResponse<List<CommentResponse>>> getCommentsByBlogId(@PathVariable("blogId") Long blogId) {
        log.info("Get comment list by blogId: {}", blogId);

        return ResponseEntity.ok(
                PageResponse
                        .<List<CommentResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách bình luận thành công")
                        .data(commentService.getCommentsByBlog(blogId))
                        .build()
        );
    }

    @Operation(summary = "create comment", description = "API create new comment")
    @PostMapping("/create")
    public ResponseEntity<PageResponse<CommentResponse>> createComment(@Valid @RequestBody CommentRequest commentRequest) {

        return ResponseEntity.ok(
                PageResponse
                        .<CommentResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Lấy danh sách bình luận thành công")
                        .data(commentService.createComment(commentRequest))
                        .build()
        );
    }

    @Operation(summary = "Delete comment", description = "API Delete comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<PageResponse<CommentResponse>> deleteComment(@PathVariable("id") String id) {

        return ResponseEntity.ok(
                PageResponse
                        .<CommentResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Xóa bình luận thành công")
                        .data(commentService.deleteComment(id))
                        .build()
        );

    }
}
