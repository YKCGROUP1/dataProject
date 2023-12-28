package com.newproduct.springboot.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String code;
    private String token;
}
