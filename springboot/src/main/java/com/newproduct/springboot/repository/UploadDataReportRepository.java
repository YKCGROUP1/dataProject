package com.newproduct.springboot.repository;

import com.newproduct.springboot.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDataReportRepository extends JpaRepository<Test, Long> {
    // 可以在这里定义自定义的查询方法或根据需要添加其他方法
}