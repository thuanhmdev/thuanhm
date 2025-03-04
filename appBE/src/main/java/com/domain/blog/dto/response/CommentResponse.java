package com.domain.blog.dto.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    private String id;
    private String content;
    private Long createdAt;
    private UserResponse user;
    private Long blogId;
    private String rootId;
}
