package com.newproduct.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
@Slf4j

public class JwtUtil {

    //常量

    public static final long expire = 6004800;
    public static final String secret = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO"; //秘钥
    private String header;

    //生成token字符串的方法
    public String getToken(String username){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //验证token字符串是否是有效的  包括验证是否过期
    public boolean checkToken(String jwtToken) {
        if(jwtToken == null || jwtToken.isEmpty()){
            log.error("Jwt is empty");
            return false;
        }
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            Claims body = claims.getBody();
            if ( body.getExpiration().after(new Date(System.currentTimeMillis()))){
                return true;
            } else
                return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public Claims getTokenBody(String jwtToken){
        if(jwtToken == null || jwtToken.isEmpty()){
            log.error("Jwt is empty");
            return null;
        }
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}