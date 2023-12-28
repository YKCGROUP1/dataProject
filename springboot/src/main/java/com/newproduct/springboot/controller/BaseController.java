package com.newproduct.springboot.controller;

import com.newproduct.springboot.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    HttpServletRequest req;

    @Autowired
    RedisUtil redisUtil;
}
