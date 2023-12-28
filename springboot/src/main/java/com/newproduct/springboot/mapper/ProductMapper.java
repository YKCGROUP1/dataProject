package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {

    @Select("select * from product where  sku like #{sku} AND yunying like concat('%' ,#{yunying} ,'%') limit #{pageNum}, #{pageSize}")
    List<Product> selectPage(Integer pageNum, Integer pageSize, String sku, String yunying); //String sku

    @Select("select count(id) from product where   sku like concat('%' ,#{sku} ,'%') AND yunying like concat('%' ,#{yunying} ,'%')") //
    Integer selectTotal(String sku, String yunying); //String sku


}
