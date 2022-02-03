package com.springad.loginblog.model;

import com.springad.loginblog.Dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comment extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postid;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Long userId;



    public Comment(CommentRequestDto requestDto, Long userId) {
        this.postid = requestDto.getPostid();
        this.comment = requestDto.getComment();
        this.username = requestDto.getUsername();
        this.userId = userId;
    }

    public Comment(CommentRequestDto requestDto, Long userId, String reply) {
        this.postid = requestDto.getPostid();
        this.comment = getComment();
        this.username = requestDto.getUsername();
        this.userId = userId;
    }


    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
