package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.RestControllerHelper;
import com.huaqi.zhanxin.service.CalendarDriver;
import com.huaqi.zhanxin.service.triangleDriver;
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
@RequestMapping("calendar")
public class CalendarController {

    @Autowired
    private CalendarDriver calendarDriver;
    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "万年历基本边界测试")
    @GetMapping("testCalendar")
    public Map<String, Object> calendarBorder() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = calendarDriver.simpleRead("calendar_border.xlsx");
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
    @ApiOperation(value = "万年历基本边界测试下载")
    @GetMapping("download/testCalendar")
    public Map<String, Object> calendarBorderDownload(HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = calendarDriver.simpleRead("calendar_border.xlsx");
        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "万年历等价类测试")
    @GetMapping("testCalendar/equivalentclass")
    public Map<String, Object> calendarEC() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = calendarDriver.simpleRead("calendar_eqvltclass.xlsx");
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "万年历等价类测试下载")
    @GetMapping("download/equivalentclass")
    public Map<String, Object> calendarECDownload(HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = calendarDriver.simpleRead("calendar_eqvltclass.xlsx");
        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "万年历决策表法测试")
    @GetMapping("testCalendar/decisionTable")
    public Map<String, Object> calendarDT() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = calendarDriver.simpleRead("calendar_decisiontable.xlsx");
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "万年历决策表法测试下载")
    @GetMapping("download/decisionTable")
    public Map<String, Object> calendarDTDownload(HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = calendarDriver.simpleRead("calendar_decisiontable.xlsx");
        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
