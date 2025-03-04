package com.domain.blog.dto.response;

import com.domain.blog.entity.Blog;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailCommentResponse {
    private String userName;
    private String commenterName;
    private String content;
    private Blog blog;
    private String blogUrl;
    private String siteName;
}
