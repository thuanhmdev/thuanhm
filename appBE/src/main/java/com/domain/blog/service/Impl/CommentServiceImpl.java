package com.domain.blog.service.Impl;

import com.domain.blog.entity.Blog;
import com.domain.blog.entity.Comment;
import com.domain.blog.entity.User;
import com.domain.blog.dto.request.CommentRequest;
import com.domain.blog.dto.response.CommentResponse;
import com.domain.blog.dto.response.MailCommentResponse;
import com.domain.blog.exception.DataNotFoundException;
import com.domain.blog.repository.CommentRepository;
import com.domain.blog.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.domain.blog.constant.RoleEnum.ADMIN;

@Service
@RequiredArgsConstructor
@Slf4j(topic="COMMENT-SERVICE")
@Tag(name="COMMENT-SERVICE")
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BlogService blogService; ;
    private final UserService userService;
    private final EmailService emailService;
    private final SettingService settingService;


    public boolean checkExistBlogAndUser(CommentRequest commentRequest) {
        boolean isExistUser = userService.existsById(commentRequest.getUserId());
        boolean isExistBlog = blogService.existsById(commentRequest.getBlogId());
        return isExistUser && isExistBlog;
    }

    @Async
    public void sendEmailNotification(CommentRequest commentRequest, User commenter) {

        String siteName = settingService.getSiteNameSetting();
        String emailAdmin = settingService.getEmailSetting();
        Blog blog = blogService.getBlogById(commentRequest.getBlogId());

        if(commentRequest.getRootId() == null || commentRequest.getRootId().isEmpty()){
            User replyToUser = userService.getUserById(commentRequest.getReplyUserId());
            if(!replyToUser.getRole().equals(ADMIN)){
                MailCommentResponse mailCommentResponse = MailCommentResponse.builder()
                        .userName("Admin")
                        .commenterName(commenter.getName())
                        .content(commentRequest.getContent())
                        .blog(blog)
                        .blogUrl(commentRequest.getBlogUrl())
                        .siteName(siteName)
                        .build();


                emailService.sendEmailFromTemplateSync(emailAdmin, "Bạn có một bình luận mới", "comment", mailCommentResponse);
            }
        } else{

            User userWriteComment = userService.getUserById(commentRequest.getUserId());
            User replyToUser =  userService.getUserById(commentRequest.getReplyUserId());
            if(replyToUser.getUsername().equals(userWriteComment.getUsername())){
                return;
            }
            if(!userWriteComment.getRole().equals(ADMIN) && !replyToUser.getRole().equals(ADMIN)){
                String email = replyToUser.getUsername();

                MailCommentResponse mailCommentResponseReplyToUser = MailCommentResponse.builder()
                        .userName(replyToUser.getName())
                        .commenterName(commenter.getName())
                        .content(commentRequest.getContent())
                        .blog(blog)
                        .blogUrl(commentRequest.getBlogUrl())
                        .siteName(siteName)
                        .build();

                MailCommentResponse mailCommentResponseReplyToAdmin = MailCommentResponse.builder()
                        .userName("Admin")
                        .commenterName(commenter.getName())
                        .content(commentRequest.getContent())
                        .blog(blog)
                        .blogUrl(commentRequest.getBlogUrl())
                        .siteName(siteName)
                        .build();


                emailService.sendEmailFromTemplateSync(email, "Bạn có một phản hồi mới", "comment", mailCommentResponseReplyToUser);
                emailService.sendEmailFromTemplateSync(emailAdmin, "Bạn có một bình luận mới", "comment", mailCommentResponseReplyToAdmin);
            }
        }

    }

    public CommentResponse createComment(CommentRequest commentRequest)  {

        Comment comment = new Comment();
        User commenter = userService.getUserById(commentRequest.getUserId());
        comment.setId(commentRequest.getId());
        comment.setRootId(commentRequest.getRootId());
        comment.setUser(commenter);
        comment.setBlog(blogService.getBlogById(commentRequest.getBlogId()));
        comment.setContent(commentRequest.getContent());

        sendEmailNotification(commentRequest, commenter);
        return convertCommentResponse(commentRepository.save(comment));
    }

    public List<CommentResponse> getComments() {
        return commentRepository.findByOrderByCreatedAtDesc().stream().map(this::convertCommentResponse).toList();
    }

    public List<CommentResponse> getCommentsByBlog(Long blogId) {
        return commentRepository.findByBlogIdOrderByCreatedAtDesc(blogId).stream().map(this::convertCommentResponse).toList();
    }

    public CommentResponse convertCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .user(userService.convertUserToUserDTO(userService.getUserById(comment.getUser().getId())))
                .createdAt(comment.getCreatedAt())
                .blogId(comment.getBlog().getId())
                .rootId(comment.getRootId())
                .build();
    }

    public Comment getCommentById (String id){
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseThrow(() -> new DataNotFoundException("Không tìm thấy bình luận này"));
    }

    public CommentResponse deleteComment(String id) {
        Comment comment = getCommentById(id);
        List<Comment> comments = commentRepository.findCommentsByRootId(id);
        commentRepository.deleteById(id);
        commentRepository.deleteAll(comments);
        return convertCommentResponse(comment);
    }

}