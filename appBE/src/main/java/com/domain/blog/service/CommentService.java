package com.domain.blog.service;

import com.domain.blog.entity.Comment;
import com.domain.blog.entity.User;
import com.domain.blog.dto.request.CommentRequest;
import com.domain.blog.dto.response.CommentResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface CommentService {

    boolean checkExistBlogAndUser(CommentRequest commentRequest);

    void sendEmailNotification(CommentRequest commentRequest, User commenter) throws MessagingException;

    CommentResponse createComment(CommentRequest commentRequest);

    List<CommentResponse> getComments();

    List<CommentResponse> getCommentsByBlog(Long blogId);

    CommentResponse convertCommentResponse(Comment comment);

    Comment getCommentById(String id);

    CommentResponse deleteComment(String id);
}