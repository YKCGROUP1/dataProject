package com.newproduct.springboot.controller;

import com.newproduct.springboot.entity.XwHistory;
import com.newproduct.springboot.mapper.XwHistoryMapper;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DateReportLogController {

    @Resource
    private XwHistoryMapper xwHistoryMapper;

    @GetMapping("/datereportlogpage")
    @PermitAll
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum-1) * pageSize;

        List<XwHistory> data = xwHistoryMapper.selectLog(pageNum,pageSize);
        Integer total = xwHistoryMapper.selectLogTotal();
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }


}
