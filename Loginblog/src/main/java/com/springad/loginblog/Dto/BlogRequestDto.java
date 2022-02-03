package com.springad.loginblog.Dto;

import lombok.Getter;

@Getter //private이니까 정보 설정 위해

public class BlogRequestDto {
    private String title; //private로 선언한 애가 final되면 바로 필요한 생성자 만들어준다.
    private String name;
    private String content;

    }
