package com.newproduct.springboot.config;

import com.newproduct.springboot.security.*;
import io.jsonwebtoken.Jwt;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Slf4j
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity

public class SecurityConfig  {

    @Resource
    LoginFailureHandler loginFailureHandler;

    @Resource
    LoginSuccessHandler loginSuccessHandler;


    @Resource
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Resource
    CaptchaFilter captchaFilter;
    @Resource
    JwtFilter jwtFilter;

    @Resource
    JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Resource
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsServiceImpl);
        return provider;
    }

    @Bean
    public UserDetailsService myDetailService() {
        return new UserDetailsServiceImpl();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean//PasswordEncoder的实现类为BCryptPasswordEncoder
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private static final String[] URL_WHITELIST = {
            "/api/login",
            "/logout",
            "/captcha",

    };

    private CorsConfigurationSource corsConfigSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(URL_WHITELIST).permitAll() //登录放行
                .anyRequest().authenticated()
        );

        //自定义账号密码过滤器
        httpSecurity.authenticationProvider(authenticationProvider());

        //禁用登录页面
//        httpSecurity.formLogin(formLogin -> formLogin
//
//                .loginProcessingUrl("/api/login")
//                .failureHandler(loginFailureHandler)
//                .successHandler(loginSuccessHandler)
//        );



        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        //禁用登出页面
        //httpSecurity.logout(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logout->logout.logoutSuccessHandler(jwtLogoutSuccessHandler));
        //禁用session
        httpSecurity.sessionManagement(AbstractHttpConfigurer::disable);
        //禁用httpBasic
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        //禁用csrf保护
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        //跨域
        httpSecurity.cors(cors -> cors
                .configurationSource(corsConfigSource()));


        httpSecurity.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
