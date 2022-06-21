package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.CalendarDriver;
import com.huaqi.zhanxin.service.TelecomDriver;
import com.huaqi.zhanxin.tools.DownloadExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("telecom")
public class TelecomController {
    @Autowired
    private TelecomDriver telecomDriver;
    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "电信收费系统测试")
    @GetMapping("testTelecom")
    public Map<String, Object> getUserName() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = telecomDriver.simpleRead();
//        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
//        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "电信收费系统测试下载")
    @GetMapping("downloadTelecom")
    public Map<String, Object> download(HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = telecomDriver.simpleRead();
        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
