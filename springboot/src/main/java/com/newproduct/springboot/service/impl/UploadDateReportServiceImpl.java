package com.newproduct.springboot.service.impl;

import com.newproduct.springboot.common.Result;
import com.newproduct.springboot.mapper.XwHistoryMapper;
import com.newproduct.springboot.service.UploadDateReportService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class UploadDateReportServiceImpl implements UploadDateReportService {


    @Resource
    private XwHistoryMapper xwHistoryMapper;
//    private static final String DATEREPORT_BASE_URL = "C:/Users/think/Desktop/projectfile/datereport";//上线后改成服务器文件地址
//    private static final String YEWUREPORT_BASE_URL = "C:/Users/think/Desktop/projectfile/yewu";//上线后改成服务器文件地址
//    private static final String BOX1REPORT_BASE_URL = "C:/Users/think/Desktop/projectfile/yueducangchu";//上线后改成服务器文件地址
//    private static final String BOX2REPORT_BASE_URL = "C:/Users/think/Desktop/projectfile/changqicangchu";//上线后改成服务器文件地址
//    private static final String BOX3REPORT_BASE_URL = "C:/Users/think/Desktop/projectfile/yamaxunkucun";//上线后改成服务器文件地址
//    private static final String SOUSUOCI_BASE_URL = "C:/Users/think/Desktop/projectfile/soucuoci";//上线后改成服务器文件地址
//    private static final String HUANHUO_BASE_URL = "C:/Users/think/Desktop/projectfile/huanhuo";//上线后改成服务器文件地址
//    private static final String TUIHUO_BASE_URL = "C:/Users/think/Desktop/projectfile/tuihuo";//上线后改成服务器文件地址
//    private static final String PRODUCT_TABLE_BASE_URL = "C:/Users/think/Desktop/projectfile/chanxiaobiao";//上线后改成服务器文件地址
//    private static final String PRODUCT_PRICE_BASE_URL = "C:/Users/think/Desktop/projectfile/chengbenbiao";//上线后改成服务器文件地址

    private static final String DATEREPORT_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/datereport";//上线后改成服务器文件地址
    private static final String YEWUREPORT_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/yewu";//上线后改成服务器文件地址
    private static final String BOX1REPORT_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/yueducangchu";//上线后改成服务器文件地址
    private static final String BOX2REPORT_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/changqicangchu";//上线后改成服务器文件地址
    private static final String BOX3REPORT_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/yamaxunkucun";//上线后改成服务器文件地址
    private static final String SOUSUOCI_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/soucuoci";//上线后改成服务器文件地址
    private static final String HUANHUO_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/huanhuo";//上线后改成服务器文件地址
    private static final String TUIHUO_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/tuihuo";//上线后改成服务器文件地址
    private static final String PRODUCT_TABLE_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/chanxiaobiao";//上线后改成服务器文件地址
    private static final String PRODUCT_PRICE_BASE_URL = "/usr/local/javaproject/projectfile/echartupload/chengbenbiao";//上线后改成服务器文件地址


    //日期范围报告
    @Override
    public Result saveDateReportFile(MultipartFile file, Integer dpid) {

        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = DATEREPORT_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
           log.error("保存文件出错",e);
           return Result.fail("保存文件出错");
        }

    }

    //月度仓储
    @Override
    public Result saveBox1ReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = BOX1REPORT_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveBox2ReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = BOX2REPORT_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveYewuReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = YEWUREPORT_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveAdReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = SOUSUOCI_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveBox3ReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = BOX3REPORT_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveHuanhuoReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = HUANHUO_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveTuihuoReportFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = TUIHUO_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveProductTableFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = PRODUCT_TABLE_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    @Override
    public Result saveProductPriceFile(MultipartFile file, Integer dpid) {
        if (file.isEmpty()) {
            return Result.fail("文件不存在");
        }
        try {
            String fileName = file.getOriginalFilename();
            String filePath = PRODUCT_PRICE_BASE_URL + File.separator + dpid + File.separator + fileName;
            File dateReportFile = new File(filePath);

            // 确保文件夹存在，如果不存在则创建
            File parentDir = dateReportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            file.transferTo(dateReportFile);
            return Result.succ("保存文件成功");
        } catch (Exception e) {
            log.error("保存文件出错",e);
            return Result.fail("保存文件出错");
        }
    }

    //插入历史记录
    @Override
    public void insertHistory(Integer dpid, String department, String name, String originFileName, String xingWei) {
        Date currentTime = new Date();

        // 将时间转换为字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = dateFormat.format(currentTime);

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("dpid",dpid);
        paramMap.put("name",name);
        paramMap.put("time",formatDate);
        paramMap.put("xingwei",xingWei);
        paramMap.put("department", department);
        paramMap.put("originfilename", originFileName);

        xwHistoryMapper.insertDateReport(paramMap);
    }





}
