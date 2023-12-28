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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    JwtUtil jwtUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        response.setHeader(jwtUtil.getHeader(),"");

        Result result = Result.succ("");

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }
}
