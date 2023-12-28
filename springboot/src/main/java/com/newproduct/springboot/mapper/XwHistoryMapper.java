package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.XwHistory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface XwHistoryMapper {
    void insertDateReport(Map<String,Object> paramMap);


    @Select("select * from xwhistory limit #{pageNum} , #{pageSize}")
    List<XwHistory> selectLog(Integer pageNum, Integer pageSize);

    @Select("select count(id) from xwhistory")
    Integer selectLogTotal();
}
