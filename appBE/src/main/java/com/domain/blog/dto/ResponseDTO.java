package com.domain.blog.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private String error;
    private Object message;
    private T data;
    private int statusCode;
}
