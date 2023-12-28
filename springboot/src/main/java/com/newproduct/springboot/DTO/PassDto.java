package com.newproduct.springboot.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PassDto implements Serializable {

    private String password;

    private String currentPass;
}
