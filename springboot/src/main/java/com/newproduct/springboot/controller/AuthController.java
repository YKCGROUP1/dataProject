package com.newproduct.springboot.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.newproduct.springboot.common.Const;
import com.newproduct.springboot.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@RestController
public class AuthController extends BaseController {

    @Autowired
    Producer producer;

    @GetMapping("/captcha")
    public Result captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();


        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg", outputStream);

        Encoder encoder = Base64.getEncoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encodeToString(outputStream.toByteArray());

        redisUtil.hset(Const.CAPTCHA_KEY,key,code,120);
        return Result.succ(
                MapUtil.builder()
                        .put("token",key)
                        .put("captchaImage",base64Img)
                        .build()
        );
    }
}
