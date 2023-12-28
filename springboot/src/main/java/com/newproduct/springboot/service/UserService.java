package com.newproduct.springboot.service;

import com.newproduct.springboot.entity.User;
import com.newproduct.springboot.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface UserService {

    User selectUserByUserName(String username);

    int updatePassword(String password, Integer id);
}
