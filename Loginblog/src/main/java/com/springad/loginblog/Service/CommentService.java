package com.springad.loginblog.Service;

import com.springad.loginblog.Dto.CommentRequestDto;
import com.springad.loginblog.Repository.CommentRepository;
import com.springad.loginblog.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository CommentRepository;

    // 댓글 조회
    public List<Comment> getComment(Long postId) {
        return CommentRepository.findAllByPostidOrderByModifiedAtDesc(postId);
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(CommentRequestDto requestDto, Long userId) {
        String commentCheck = requestDto.getComment();
        if (commentCheck.contains("script")|| commentCheck.contains("<")||commentCheck.contains(">")){
            Comment comment = new Comment(requestDto, userId,"xss 안돼요,, 하지마세요ㅠㅠ");
            CommentRepository.save(comment);
            return comment;
        }
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Comment comment = new Comment(requestDto, userId);
        CommentRepository.save(comment);
        return comment;
    }

    // 댓글 수정
    @Transactional
    public void update(Long id, CommentRequestDto requestDto) {
        Comment Comment = CommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        Comment.update(requestDto);
    }
}
