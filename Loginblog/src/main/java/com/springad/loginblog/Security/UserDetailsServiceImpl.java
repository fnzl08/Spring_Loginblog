package com.springad.loginblog.Security;


import com.springad.loginblog.Repository.UserRepository;
import com.springad.loginblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { //userdetailservice이거 인터페이스 구현해서 쓰고 있는데 들어가보면 뭘 구현해야하는지 함수가 정의되어 있고 이거 꼭 정의해줘야한다. 그게 밑에 loaduserbyusername ; username 찾아와라


    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));

        return new UserDetailsImpl(user); //이 인터페이스로 user를 넘겨주기. 이것도 보면 정해진 함수 있다. impl은 회원정보 user를 담고있는 객체
    }
}
