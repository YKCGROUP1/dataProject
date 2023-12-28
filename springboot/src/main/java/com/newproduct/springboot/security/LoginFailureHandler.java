package com.newproduct.springboot.security;

import cn.hutool.json.JSONUtil;
import com.newproduct.springboot.common.CaptchaException;
import com.newproduct.springboot.common.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import org.springframework.security.core.AuthenticationException;

@Component
public  class LoginFailureHandler implements AuthenticationFailureHandler {




    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        String msg = "用户名或密码错误";

        if (exception instanceof CaptchaException) {
            msg = exception.getMessage();
        }
        Result result = Result.fail(msg);
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }
}
