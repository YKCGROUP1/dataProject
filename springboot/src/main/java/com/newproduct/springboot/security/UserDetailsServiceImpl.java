package com.newproduct.springboot.security;

import com.newproduct.springboot.entity.User;
import com.newproduct.springboot.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("开始登录验证,用户名为： {}",username);
        try {
            User user = userService.selectUserByUserName(username);
            return user;
        } catch (Exception e) {
            log.error("user is not find");
            return null;
        }
    }
}
