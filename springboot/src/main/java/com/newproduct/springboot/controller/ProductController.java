package com.newproduct.springboot.controller;

import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    @GetMapping("/productpage")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(required = false) String sku,
                                        @RequestParam(required = false) String yunying) {

        pageNum = (pageNum-1) * pageSize;
        sku = '%' + sku +'%';
        yunying = '%' + yunying +'%';
        List<Product> data = productMapper.selectPage(pageNum,pageSize,sku,yunying);
        Integer total = productMapper.selectTotal(sku,yunying);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }




}
