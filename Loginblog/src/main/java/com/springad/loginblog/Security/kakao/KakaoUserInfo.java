package com.springad.loginblog.Security.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class KakaoUserInfo {
    Long id;
    String email;
    String nickname;
}