package com.springad.loginblog.Service;

import com.springad.loginblog.Dto.BlogRequestDto;
import com.springad.loginblog.Repository.BlogRepository;
import com.springad.loginblog.model.Blog;
import jdk.javadoc.internal.doclets.formats.html.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//이 클래스가 서비스임을 명시. 업데이트 일어날 수 있어 알아둬
@Service
@RequiredArgsConstructor //가 밑에꺼 롬복으로 코드 줄인것, final로 선언된 애 있으면 그거 생성할 때 같이 넣어줄게
////생성자를 통해 blogrepo를 받아서 service만들 때 꼭 repo에 넣어주도록 스프링에게 알려줌
//public BlogService(BlogRepository blogRepository) {
//        this.blogRepository = blogRepository;
//        }

public class BlogService {
    private final BlogRepository blogRepository;   //final; 서비스에서 꼭 필요한 앤걸 명시, 변형될 수 없다.
    //검색이나 업데이트 때 repo필요


//    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
//    public Blog createBlog(BlogRequestDto requestDto, String username) {
//        String contentsCheck = requestDto.getContent();
//        String titleCheck = requestDto.getTitle();
//        if (contentsCheck.contains("script")||contentsCheck.contains("<")||contentsCheck.contains(">")){
//            Blog blog = new Blog(requestDto,username,"_");
//            BlogRepository.save(blog);
//        }
//        if (titleCheck.contains("script")||titleCheck.contains("<")||titleCheck.contains(">")) {
//            Blog blog = new Blog("_", username, "_");
//            BlogRepository.save(blog);
//            return blog;
//        }
//        // 요청받은 DTO 로 DB에 저장할 객체 만들기
//        Blog blog = new Blog(requestDto, username);
//        BlogRepository.save(blog);
//        return blog;
//    }


    @Transactional   //업데이트 일어나면 어떤게 업데이트 됐는지 아이디를 돌려준다. 전달받는거 재료 두 개 필요.
    //sql 쿼리가 일어나야함을 알려주는 것.
    public Long update(Long id, BlogRequestDto requestDto){   //id는 어떤 녀석을 업데이트 할 것인지, 다음은 업데이트 할 정보를 가져오는 애
        Blog blog = blogRepository.findById(id).orElseThrow(   //일단 id 찾아보고
                ()-> new IllegalArgumentException("일지가 없어..") //없으면
        );
        blog.update(requestDto);    //업데이트되도록 메소드 설정하면 전달받은 blog 정보가 파라미터로 넘어가 업뎃되고 리턴
        return blog.getId();
    }
}