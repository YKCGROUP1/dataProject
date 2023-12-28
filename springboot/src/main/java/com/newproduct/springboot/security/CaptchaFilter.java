package com.newproduct.springboot.security;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newproduct.springboot.common.CaptchaException;
import com.newproduct.springboot.common.Const;
import com.newproduct.springboot.common.CustomRequestWrapper;
import com.newproduct.springboot.common.Result;
import com.newproduct.springboot.utils.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    RedisUtil redisUtil;

    @Resource
    LoginFailureHandler loginFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("开始验证码过滤");
        String url = request.getRequestURI();
        if ("/uploadfile/datereport".equals(url)
                || "/uploadfile/yueducangchureport".equals(url)
                || "/uploadfile/changqicangchureport".equals(url)
                || "/uploadfile/yewureport".equals(url)
                || "/uploadfile/searchkeyreport".equals(url)
                || "/uploadfile/amazonkucunreport".equals(url)
                || "/uploadfile/huanhuoreport".equals(url)
                || "/uploadfile/tuihuoreport".equals(url)
                || "/uploadfile/chanxiaobiao".equals(url)
                || "/uploadfile/chengbenbiao".equals(url) ) {
            // 如果是要跳过的路径，直接放行
            filterChain.doFilter(request, response);
            return ;
        }
        // 这个是使用了一层自定义包装类,对request中的InputStream做备份
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        CustomRequestWrapper requestWrapper = new CustomRequestWrapper(httpRequest);

        log.info("验证码过滤的请求url"+ url);
        if ("/api/login".equals(url) && request.getMethod().equals("POST")) {
            // 校验验证码
            try {
                validate(requestWrapper);
            } catch (CaptchaException e) {
                response.setContentType("application/json;charset=UTF-8");
                ServletOutputStream outputStream = response.getOutputStream();
                String msg = e.getMessage();
                Result result = Result.fail(msg);
                outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));

                outputStream.flush();
                outputStream.close();

            }
        }else{
            log.info("跳过非/login请求");
        }

        filterChain.doFilter(requestWrapper, response);
    }

    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) throws CaptchaException, IOException {
        InputStream inputStream = httpServletRequest.getInputStream();
        String requestBody = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(requestBody);

        String code = jsonNode.get("code").asText();
        String key = jsonNode.get("token").asText();

        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY, key))) {
            System.out.println("2");
            throw new CaptchaException("验证码错误");
        }

        // 验证码一次性使用
        redisUtil.hdel(Const.CAPTCHA_KEY, key);
    }

}
