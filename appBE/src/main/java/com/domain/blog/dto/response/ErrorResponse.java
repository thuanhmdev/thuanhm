package com.domain.blog.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
    private String message;
}
