package com.springad.loginblog.Repository;


import com.springad.loginblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostidOrderByModifiedAtDesc(Long postId);
}