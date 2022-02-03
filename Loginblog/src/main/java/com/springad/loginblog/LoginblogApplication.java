package com.springad.loginblog;

import com.springad.loginblog.Repository.BlogRepository;
import com.springad.loginblog.Repository.CommentRepository;
import com.springad.loginblog.model.Blog;
import com.springad.loginblog.model.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class LoginblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginblogApplication.class, args);
    }

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));

    }

    @Bean
    public CommandLineRunner demo(BlogRepository repository) {
        return (args) -> {
            repository.save(new Blog("사실은", "ㅎㅈ", "아직은 항해보단 조난에 가깝.."));
        };
    }

        }

