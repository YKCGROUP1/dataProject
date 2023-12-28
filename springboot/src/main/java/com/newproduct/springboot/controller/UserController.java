package com.newproduct.springboot.controller;

import com.newproduct.springboot.DTO.PassDto;
import com.newproduct.springboot.common.MenuItem;
import com.newproduct.springboot.common.Result;
import com.newproduct.springboot.entity.User;
import com.newproduct.springboot.service.UserService;
import com.newproduct.springboot.utils.JwtUtil;
import com.newproduct.springboot.DTO.LoginRequest;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserService userService;

    @Resource
    PasswordEncoder passwordEncoder;


    //登录接口
    @PostMapping("/login")
    public Result doLogin(@RequestBody LoginRequest request){
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());
        try{
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            Authentication authentication = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtil.getToken(userDetails.getUsername());
            log.info(jwtToken);
            return Result.succ(200,"登录成功",jwtToken);
        } catch (Exception e){
            log.error(e.getMessage());
            log.error("账号或密码错误");
            return Result.fail("登录失败");
        }
    }


    //获取侧边栏接口 暂时不做任何权限控制
    @GetMapping("/nav")
    @PermitAll
    public Result getMenuNav() {
        log.info("到达navcontroller了");
        MenuItem dataShow = new MenuItem();
        dataShow.setName("DataShow");
        dataShow.setTitle("数据看板");
        dataShow.setComponent("DataView");
        dataShow.setPath("/data");

        MenuItem dataUpdate = new MenuItem();
        dataUpdate.setName("DataUpdate");
        dataUpdate.setTitle("更新数据");
        dataUpdate.setComponent("TestView");
        dataUpdate.setPath("/test");

        MenuItem dataPart = new MenuItem();
        dataPart.setName("DataPart");
        dataPart.setTitle("YKCBI");
        dataPart.setComponent("");
        dataPart.setPath("");

        dataPart.addChild(dataShow);
        dataPart.addChild(dataUpdate);

        MenuItem productShow = new MenuItem();
        productShow.setName("ProductShow");
        productShow.setTitle("产销表");
        productShow.setComponent("ProductView");
        productShow.setPath("/product");

        MenuItem productPart = new MenuItem();
        productPart.setName("ProductPart");
        productPart.setTitle("产品");
        productPart.setComponent("");
        productPart.setPath("");

        productPart.addChild(productShow);

        List<MenuItem> nav = new ArrayList<>();
        nav.add(dataPart);
        nav.add(productPart);

        return Result.succ(200,"操作成功",nav);
    }


    //获取当前用户信息 不做任何权限控制
    @GetMapping("/userInfo")
    @PermitAll
    public Result gerUserInfo(Principal principal){

        String username = principal.getName();
        User user = userService.selectUserByUserName(username);
        Map<String, Object> res = new HashMap<>();
        res.put("department",user.getDepartment());
        res.put("dpid",user.getDpid());
        res.put("name",user.getName());
        return Result.succ(200,"获取用户信息成功",res);

    }


    //修改密码接口 不做任何权限控制
    @PostMapping("/updatePass")
    public Result updatePass(@Validated @RequestBody PassDto passDto, Principal principal) {
        //查询当前用户的密码等
        User user = userService.selectUserByUserName(principal.getName());

        boolean matches = passwordEncoder.matches(passDto.getCurrentPass(), user.getPassword());
        if(!matches) {
            return Result.fail("旧密码不正确");
        }
        userService.updatePassword(passwordEncoder.encode(passDto.getPassword()), user.getId());
        return Result.succ(200,"修改成功,即将重新登录",null);

    }
}



