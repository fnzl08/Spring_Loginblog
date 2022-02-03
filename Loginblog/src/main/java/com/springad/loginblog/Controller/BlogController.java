package com.springad.loginblog.Controller;


import com.springad.loginblog.Dto.BlogRequestDto;
import com.springad.loginblog.Repository.BlogRepository;
import com.springad.loginblog.Repository.CommentRepository;
import com.springad.loginblog.Security.UserDetailsImpl;
import com.springad.loginblog.Service.BlogService;
import com.springad.loginblog.model.Blog;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//restcontroller만들 때도 알아서 넣어줄게, 요청오면 알아서 해줄게
@RestController
@RequiredArgsConstructor

public class BlogController{

    private final BlogRepository blogRepository;
    private final BlogService blogService;
    private final CommentRepository commentRepository;


    //전체 조회
    //Read, GET방식. {반환방식 리스트 메모, 필요재료 없고 찾을 땐 repo써서 다 찾아서 modifiedat이라는 변수가 있는데 그걸로 최신청렬해줘
    @GetMapping("/api/hjblog")
    public List<Blog> getBlog() { return blogRepository.findAllByOrderByModifiedAtDesc();}


    // 게시글 특정 조회
    @GetMapping("/api/hjblog/{id}")
    public Blog getBlog(@PathVariable Long id) {
        Blog blog =  blogRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("id가 존재하지 않습니다."));
        return blog;
    }


    //게시글 작성
    //Create, Post방식으로 요청이 들어올거다.는 restcontroller / 요청오면 아래 메소드 진행
    @PostMapping("/api/hjblog/write")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUser().getUsername(); //로그인한 사람 ID
        Blog blog = new Blog(requestDto);  //requestDto에 있는 데이터가 알아서 blog 들어가 채워줄거다.
        return blogRepository.save(blog); //방금 생성한 blog 리턴
    }


    //Delete, 뭐 받아서 삭제할지, id니까 long해주고, id 아직 뭔지 모르니까 주소로 들어올 때 path 경로에 중괄호에 있는거 받을게
    @DeleteMapping("/api/hjblog/{id}")
    public Long deleteBlog(@PathVariable Long id){
        System.out.println(id);
        blogRepository.deleteById(id);
        return id;
    }

    //수정
    //Update, 주소에서 받아와야하니까 @path 에 id값 들어가고, 바디로 오는건 requestdto에 들어간다.
    //id 관련 메모 찾아서 requestdto에 있는 내용으로 업데이트
    @PutMapping("/api/hjblog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        return blogService.update(id, requestDto);
    }

}
