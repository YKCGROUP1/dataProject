package com.newproduct.springboot.security;

import cn.hutool.json.JSONUtil;
import com.newproduct.springboot.common.Result;
import com.newproduct.springboot.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    JwtUtil jwtUtil;

    @Override
    public void  onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        //生成jwt，并放置到请求头中
        String jwt = jwtUtil.getToken(authentication.getName());
        response.setHeader(jwtUtil.getHeader(), jwt);

        Result result = Result.succ(jwt);

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }

}
