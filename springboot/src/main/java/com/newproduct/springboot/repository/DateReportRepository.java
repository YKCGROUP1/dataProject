package com.newproduct.springboot.repository;

import com.newproduct.springboot.entity.DateReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateReportRepository extends JpaRepository<DateReport, Integer> {
    // 可以在这里添加自定义的查询方法
}