package com.newproduct.springboot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 返回到前端页面的工具类
*
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private int code; //状态码
    private String msg;//消息提示
    private Object data; //数据
}
