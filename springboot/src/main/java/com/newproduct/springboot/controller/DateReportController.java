package com.newproduct.springboot.controller;



import com.newproduct.springboot.entity.Test;
import com.newproduct.springboot.entity.User;
import com.newproduct.springboot.service.UploadDateReportService;
import com.newproduct.springboot.service.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/uploadfile")
public class DateReportController {
    @Resource
    UserService userService;

    private final UploadDateReportService uploadDateReportService;

    @Autowired
    public DateReportController(UploadDateReportService uploadDateReportService) {
        this.uploadDateReportService = uploadDateReportService;
    }
    //日期范围报告
    @PostMapping("/datereport")
    public void DateReportFileUpload(
            @RequestParam("dateReportFile")MultipartFile dateReportFile, Principal principal){
        String originFileName = dateReportFile.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveDateReportFile(dateReportFile,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传日期范围报告");
    }

    //月度仓储
    @PostMapping("/yueducangchureport")
    public void YueducangchuFileUpload(
            @RequestParam("box1File")MultipartFile box1File, Principal principal){
        String originFileName = box1File.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveBox1ReportFile(box1File,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传月度仓储费报告");
    }


    //长期仓储
    @PostMapping("/changqicangchureport")
    public void ChangqicangchuFileUpload(
            @RequestParam("box2File")MultipartFile box2File, Principal principal){
        String originFileName = box2File.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveBox2ReportFile(box2File, user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传长期仓储费报告");
    }

    //业务报告
    @PostMapping("/yewureport")
    public void YewuFileUpload(
            @RequestParam("yewuReportFile")MultipartFile yewuReportFile, Principal principal ){
        String originFileName = yewuReportFile.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveYewuReportFile(yewuReportFile,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传业务报告");
    }

    //搜索词报告
    @PostMapping("/searchkeyreport")
    public void SeacherKeyFileUpload(
            @RequestParam("sousuociFile")MultipartFile sousuociFile, Principal principal) {
        String originFileName = sousuociFile.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveAdReportFile(sousuociFile,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传搜索词报告");
    }

    //亚马逊库存
    @PostMapping("/amazonkucunreport")
    public void AmazonKucunFileUpload(
            @RequestParam("box3File")MultipartFile box3File, Principal principal){
        String originFileName = box3File.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveBox3ReportFile(box3File,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传亚马逊库存报告");
    }

    //换货
    @PostMapping("/huanhuoreport")
    public void HuanhuoFileUpload(
            @RequestParam("huanHuoFile")MultipartFile huanHuoFile, Principal principal) {
        String originFileName = huanHuoFile.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveHuanhuoReportFile(huanHuoFile,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传换货报告");
    }

    //退货
    @PostMapping("/tuihuoreport")
    public void TuihuoFileUpload(
            @RequestParam("tuiHuoFile")MultipartFile tuiHuoFile, Principal principal) {
        String originFileName = tuiHuoFile.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveTuihuoReportFile(tuiHuoFile,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传退货报告");
    }

    //产销表
    @PostMapping("/chanxiaobiao")
    public void productTableFileUpload(
            @RequestParam("productTableFile")MultipartFile productTableFile, Principal principal) {
        String originFileName = productTableFile.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveProductTableFile(productTableFile,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传产销表");
    }

    //退货
    @PostMapping("/chengbenbiao")
    public void productPriceFileUpload(
            @RequestParam("productPriceFile")MultipartFile productPrice, Principal principal) {
        String originFileName = productPrice.getOriginalFilename();
        User user = userService.selectUserByUserName(principal.getName());
        uploadDateReportService.saveProductPriceFile(productPrice,user.getDpid());
        uploadDateReportService.insertHistory(user.getDpid(), user.getDepartment(), user.getName(), originFileName, "上传产品成本表");
    }



}


