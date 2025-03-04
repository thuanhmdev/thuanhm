package com.domain.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private String id;

    @NotBlank(message = "Nội dung không được bỏ trống")
    private String content;
    private Long createdAt;
    private String rootId;
    private String userId;
    private String replyUserId;
    private Long blogId;
    private String blogUrl;
}
