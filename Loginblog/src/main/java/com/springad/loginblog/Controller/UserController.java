package com.springad.loginblog.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springad.loginblog.Dto.SignupRequestDto;
import com.springad.loginblog.Security.UserDetailsImpl;
import com.springad.loginblog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {

        return "login";
    }

    //오류
    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signUp")
    public String signUp() {

        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signUp")
    public String registerUser(SignupRequestDto requestDto, Model model) {
        if (userService.registerUser(requestDto).equals("")) {
        return "login";
    } else {
        model.addAttribute("errortext", userService.registerUser(requestDto));
        return "signup";
    }
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code)  {
        userService.kakaoLogin(code);
        return "redirect:/";
    }

    // 유저 로그인 체크
    @GetMapping("/api/userCheck")
    public String userCheck(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return userDetails.getUser().getUsername();
        }
        return "";
    }


}
