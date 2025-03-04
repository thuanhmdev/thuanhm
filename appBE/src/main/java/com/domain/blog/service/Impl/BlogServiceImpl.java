package com.domain.blog.service.Impl;

import com.domain.blog.entity.Blog;
import com.domain.blog.entity.User;
import com.domain.blog.dto.response.BlogResponse;
import com.domain.blog.dto.response.PaginationListResponse;
import com.domain.blog.exception.DataNotFoundException;
import com.domain.blog.repository.BlogRepository;
import com.domain.blog.service.BlogService;
import com.domain.blog.service.CategoryService;
import com.domain.blog.service.JwtService;
import com.domain.blog.service.UserService;
import com.domain.blog.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.domain.blog.constant.FileConstant.allowedFileExtension;
import static com.domain.blog.constant.FileConstant.baseURI;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final CategoryService categoryService;

    public List<BlogResponse> getBlogs() {
        List<Blog> blogs = blogRepository.findAllByOrderByCreatedAtDesc();
        return blogs.stream().map(this::convertBlogResponse).toList();
    }

    public List<BlogResponse> getRelatedBlogs(Long blogId) {
        String categoryId = getBlogById(blogId).getCategory().getId();
        List<Blog> blogs = blogRepository.findTop5ByCategoryIdOrderByCreatedAtDesc(categoryId);
        return blogs.stream().map(this::convertBlogResponse).toList();
    }

    public PaginationListResponse<List<BlogResponse>> getBlogPaginationList(String keyword, String category, String sort, int page, int size) {
        log.info("getBlogPaginationList start");
        /* Handle Sort */
        //Sort full list - DESC
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createdAt");
        //checking keyword short and handle sort by keyword
        if(StringUtils.hasLength(sort)){
            Pattern pattern = Pattern.compile("^(\\w+?)(:)(.*)$");
            Matcher matcher = pattern.matcher(sort);
            if(matcher.find()){
                String columnName = matcher.group(1);
                Sort.Direction direction = matcher.group(3).equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
                order = new Sort.Order(direction, columnName);
            }
        }
        /* Convert page start by O */
        int pageNo = page > 0 ? page - 1 : 0;

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(order));

        Page<Blog> pageEntities;
        /* Check exist keyword and category*/
        if(StringUtils.hasLength(keyword)){
            if(StringUtils.hasLength(category)){
                pageEntities =  blogRepository.getPaginationListByKeywordAndCategory(keyword, category, pageable);
            } else{
                pageEntities =  blogRepository.getPaginationListByKeyword(keyword, pageable);
            }
        } else{
            if(StringUtils.hasLength(category)){
                pageEntities =  blogRepository.getPaginationListByCategory(category, pageable);
            } else{
                pageEntities =  blogRepository.findAll(pageable);
            }
        }


        List<BlogResponse> blogResponseList = pageEntities.getContent().stream().map(this::convertBlogResponse).toList();
        return PaginationListResponse
                .<List<BlogResponse>>builder()
                .page(page)
                .size(size)
                .totalPages(pageEntities.getTotalElements())
                .totalPages(pageEntities.getTotalPages())
                .data(blogResponseList).build();
    }

    public Blog getBlogById(Long id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        return blogOptional.orElse(null);
    }

    public BlogResponse getBlogResponseById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Bài viết không tồn tại"));
        return convertBlogResponse(blog);
    }

    public BlogResponse createBlog(MultipartFile file, String title, String content, String description, String keyword, String categoryId) {
        log.info("Creating blog");
        try {
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setDescription(description);
            blog.setKeyword(keyword);
            blog.setCategory(categoryService.findById(categoryId));

            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                boolean isValid = allowedFileExtension.stream().anyMatch(item -> fileName.toLowerCase().endsWith(item));
                if (isValid) {
                    //Create a directory if not Exist
                    FileUtil.createDirectory(baseURI + "blog");
                    //Store file and save name to DB
                    String fileNameFinal = FileUtil.storeFile(file, "blog");
                    blog.setImage(("/" +"blog" + "/" + fileNameFinal));
                }
            }
            String usernameBlogger = jwtService.getCurrentUserLogin().isPresent() ? jwtService.getCurrentUserLogin().get() : null;

            User blogger = userService.getUserByUsername(usernameBlogger);
            blog.setBlogger(blogger);

            return convertBlogResponse(blogRepository.save(blog));

        } catch(URISyntaxException | IOException e) {
            log.error("Error when create blog, message = {}", e.getMessage());
            return null;
        }
    }

    public BlogResponse updateBlog(MultipartFile file, Long id, String title, String content, String description, String keyword, String img, String categoryId) {
        log.info("Updating blog");
        try {
            Blog blogUpdate = getBlogById(id);

            if (blogUpdate == null) {
                return null;
            }
            blogUpdate.setTitle(title);
            blogUpdate.setContent(content);
            blogUpdate.setDescription(description);
            blogUpdate.setKeyword(keyword);
            blogUpdate.setCategory(categoryService.findById(categoryId));
            blogUpdate.setImage(img);

            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                boolean isValid = allowedFileExtension.stream().anyMatch(item -> fileName.toLowerCase().endsWith(item));
                if (isValid) {

                    // Delete old file if exists
                    if(blogUpdate.getImage() != null && !blogUpdate.getImage().isEmpty()){
                        String oldFileName = blogUpdate.getImage().replace("/blog/", "");
                        FileUtil.deleteOldFile(oldFileName, "blog");
                    }

                    //Create a directory if not Exist
                    FileUtil.createDirectory(baseURI + "blog");
                    //Store file and save name to DB
                    String fileNameFinal = FileUtil.storeFile(file, "blog");
                    blogUpdate.setImage(("/" +"blog" + "/" + fileNameFinal));
                }

            } else {
                if (Objects.equals(img, "") || img == null) {
                    // Delete old file if exists
                    if(blogUpdate.getImage() != null && !blogUpdate.getImage().isEmpty()){
                        FileUtil.deleteOldFile(blogUpdate.getImage(), "blog");
                    }
                    blogUpdate.setImage(null);
                } else {
                    blogUpdate.setImage(blogUpdate.getImage());
                }
            }
            blogRepository.save(blogUpdate);
            return convertBlogResponse(blogUpdate);
        } catch(URISyntaxException | IOException e) {
            log.error("Error when update blog id = {}, message = {}", id, e.getMessage());
            return null;
        }

    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }


    public BlogResponse convertBlogResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .image(blog.getImage())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .description(blog.getDescription())
                .keyword(blog.getKeyword())
                .blogger(userService.convertUserResponse(blog.getBlogger()))
                .category(categoryService.findCategoryResponseById(blog.getCategory().getId()))
                .build();
    }


    public boolean existsById(Long id){
        return blogRepository.existsById(id);
    }
}
