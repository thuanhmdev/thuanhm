package com.domain.blog.controller;

import com.domain.blog.dto.response.BlogResponse;
import com.domain.blog.dto.response.PageResponse;
import com.domain.blog.dto.response.PaginationListResponse;
import com.domain.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blog-api")
@RequiredArgsConstructor
@Tag(name = "BLOG-CONTROLLER")
@Slf4j(topic = "BLOG-CONTROLLER")
public class BlogController {
    private final BlogService blogService;

    @Operation(summary = "Get full blog list ", description = "API get full blog list")
    @GetMapping("/blog/list")
    public ResponseEntity<PageResponse<List<BlogResponse>>> getBlogs() {
        log.info("Get full blog list");
        return ResponseEntity.ok(
                PageResponse
                        .<List<BlogResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách bài viết thành công")
                        .data(blogService.getBlogs())
                        .build()
        );
    }

    @Operation(summary = "Get pagination blog list ", description = "API get pagination blog list")
    @GetMapping("/blog/pagination-list")
    public ResponseEntity<PageResponse<PaginationListResponse<List<BlogResponse>>>> getBlogPaginationList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category
    ) {
        log.info("Get pagination blog list");
        PaginationListResponse<List<BlogResponse>> paginationBlogList = blogService.getBlogPaginationList(keyword, category, sort, page, size);

        return ResponseEntity.ok(
                PageResponse
                        .<PaginationListResponse<List<BlogResponse>>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách bài viết thành công")
                        .data(paginationBlogList)
                        .build()
        );
    }


    @Operation(summary = "Get related blog list ", description = "API get related blog list")
    @GetMapping("/blog/related-list")
    public ResponseEntity<PageResponse<List<BlogResponse>>> getRelatedBlogList(
            @RequestParam Long id
    ) {

        return ResponseEntity.ok(
                PageResponse
                        .<List<BlogResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy danh sách bài viết thành công")
                        .data(blogService.getRelatedBlogs(id))
                        .build()
        );
    }

    @Operation(summary = "Get blog detail", description = "API retrieve blog detail by ID from database")
    @GetMapping("/blog/{id}")
    public ResponseEntity<PageResponse<BlogResponse>> getBlogById(@PathVariable("id") Long id) {
        log.info("Get related blogs by ID: {}", id);
        BlogResponse blog = blogService.getBlogResponseById(id);

        return ResponseEntity.ok(
                PageResponse
                        .<BlogResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Lấy bài viết thành công")
                        .data(blog)
                        .build()
        );
    }


    @Operation(summary = "Create blog", description = "API create new blog")
    @PostMapping("/blog/create")
    public ResponseEntity<PageResponse<BlogResponse>> createBlog(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "categoryId") String categoryId
    ) {
        log.info("Create new blog with title: {}", title);


        BlogResponse blogResponse = blogService.createBlog(file, title, content, description, keyword, categoryId);

        return ResponseEntity.ok(PageResponse
                .<BlogResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Tạo bài viết thành công")
                .data(blogResponse)
                .build());
    }

    @Operation(summary = "Update blog", description = "API update blog")
    @PutMapping("/blog/update")
    public ResponseEntity<PageResponse<BlogResponse>> updateBlog(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "image", required = false) String image,
            @RequestParam(name = "categoryId", required = true) String categoryId
    ) {
        log.info("Update blog with id: {}", id);

        BlogResponse blogUpdated = blogService.updateBlog(file, id, title, content, description, keyword, image, categoryId);

        return ResponseEntity.ok(PageResponse
                .<BlogResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Cập nhật bài viết thành công")
                .data(blogUpdated)
                .build());
    }

    @Operation(summary = "Delete blog", description = "API Delete blog")
    @DeleteMapping("/blog/{id}")
    public ResponseEntity<PageResponse<BlogResponse>> deleteBlog(@PathVariable("id") Long id) {
        log.info("Delete blog with id: {}", id);
        BlogResponse blog = blogService.getBlogResponseById(id);

        blogService.deleteBlog(id);
        return ResponseEntity.ok(PageResponse
                .<BlogResponse>builder()
                .status(HttpStatus.ACCEPTED.value())
                .message("Cập nhật bài viết thành công")
                .data(blog)
                .build());
    }
}