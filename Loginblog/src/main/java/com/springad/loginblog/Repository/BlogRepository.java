package com.springad.loginblog.Repository;

import com.springad.loginblog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long>{

    List<Blog> findAllByOrderByModifiedAtDesc();
}
