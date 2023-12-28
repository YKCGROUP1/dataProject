package com.newproduct.springboot.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncryption {

    public static void main(String[] args) {
        String rawPassword = "ykccky123456";

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);
    }
}