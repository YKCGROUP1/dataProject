package com.newproduct.springboot.service;

import com.newproduct.springboot.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadDateReportService {

    //日期范围报告
    Result saveDateReportFile(MultipartFile files, Integer dpid);

    //月度仓储
    Result saveBox1ReportFile(MultipartFile files, Integer dpid);

    //长期仓储
    Result saveBox2ReportFile(MultipartFile files, Integer dpid);

    //业务报告
    Result saveYewuReportFile(MultipartFile files, Integer dpid);

    //搜索词报告
    Result saveAdReportFile(MultipartFile files, Integer dpid);

    //亚马逊库存
    Result saveBox3ReportFile(MultipartFile files, Integer dpid);

    //换货
    Result saveHuanhuoReportFile(MultipartFile files, Integer dpid);

    //退货
    Result saveTuihuoReportFile(MultipartFile files, Integer dpid);

    //产销表
    Result saveProductTableFile(MultipartFile files, Integer dpid);

    //成本表
    Result saveProductPriceFile(MultipartFile files, Integer dpid);


    void insertHistory(Integer dpid, String department, String name, String originFileName, String xingWei);


}
