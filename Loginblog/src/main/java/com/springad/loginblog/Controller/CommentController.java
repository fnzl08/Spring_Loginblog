package com.springad.loginblog.Controller;

import com.springad.loginblog.Dto.CommentRequestDto;
import com.springad.loginblog.Repository.CommentRepository;
import com.springad.loginblog.Security.UserDetailsImpl;
import com.springad.loginblog.Service.CommentService;
import com.springad.loginblog.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository CommentRepository;
   private final CommentService CommentService;

    //특정 게시글 조회
    @GetMapping("/api/comment")
    public List<Comment> getComment(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return CommentService.getComment(userId);
    }

    // 게시글 id 로 댓글 조회
    @GetMapping("/api/comment/{postid}")
    public List<Comment> getComment(@PathVariable Long postid) {
        return CommentService.getComment(postid);
    }

    // 댓글 작성
    @PostMapping("/api/comment")
    public boolean createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        if (userDetails != null) {
            Long userId = userDetails.getUser().getId();
            CommentService.createComment(requestDto, userId);
            return true;
        }
        return false;
    }

    // 댓글 수정
    @PutMapping("/api/comment/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        CommentService.update(id, requestDto);
        return id;
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public Long deleteComment(@PathVariable Long id) {
        CommentRepository.deleteById(id);
        return id;
    }
}