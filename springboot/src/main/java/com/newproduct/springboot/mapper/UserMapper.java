package com.newproduct.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newproduct.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    User selectUserByUserName(String username);

    int updatePassword(String password,Integer id);


}
