package com.newproduct.springboot.service.impl;

import com.newproduct.springboot.entity.User;
import com.newproduct.springboot.mapper.UserMapper;
import com.newproduct.springboot.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User selectUserByUserName(String username) {
        log.info("开始验证账号密码");
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public int updatePassword(String password, Integer id) {
        log.info("开始修改密码");
        return  userMapper.updatePassword(password,id);
    }
}
