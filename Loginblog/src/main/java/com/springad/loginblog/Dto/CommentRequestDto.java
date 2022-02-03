package com.springad.loginblog.Dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long postid;
    private String username;
    private String comment;
    private Long userId;
}