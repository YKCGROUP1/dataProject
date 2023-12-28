package com.newproduct.springboot.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@Entity
@Table(name = "user")
public class User implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@TableField("name")
    private String name;

    //@TableField("department")
    private String department;

    //@TableField("dpid")
    private Integer dpid;

   // @TableField("password")
    private String password;

    //@TableField("username")
    private String username;

    //@TableField("writeaut")
    private String writeaut;


    @Override//实现UserDetails的getPassword方法，返回实体类的password
    public String getPassword() {
        return password;
    }

    @Override//这个方法是UserDetails中的方法，必须实现
    public String getUsername() {
        return username;
    }

    public String getUserName(){//这个是mybatis-plus需要用到的方法
        return this.username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    //账号没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号没被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //密码没过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //账号还能用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
