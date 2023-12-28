package com.newproduct.springboot.security;

import com.newproduct.springboot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@Slf4j
public class JwtFilter  extends OncePerRequestFilter {

    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserDetailsServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        if (requestUrl.equals("/captcha")  ) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization");//从请求头中获取token
        log.info("jwt请求url"+requestUrl);
        log.info(jwtToken);
        if (jwtToken != null && !jwtToken.isEmpty() && jwtUtil.checkToken(jwtToken)) {
            try {//token可用
                Claims claims = jwtUtil.getTokenBody(jwtToken);
                String userName = (String) claims.get("sub");
                UserDetails user = userService.loadUserByUsername(userName);
                log.info(requestUrl , user);
                if (user != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            log.warn("token is null or empty or out of time, probably user is not log in !");
        }
        filterChain.doFilter(request, response);//继续过滤

    }
}

