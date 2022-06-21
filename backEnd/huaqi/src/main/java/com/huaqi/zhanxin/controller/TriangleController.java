package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.triangleDriver;
import com.huaqi.zhanxin.tools.DownloadExcelUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("triangle")
public class TriangleController {
    @Autowired
    private triangleDriver triangleDriver;

    RestControllerHelper helper = new RestControllerHelper();

    @ApiOperation(value = "三角形边界值测试")
    @GetMapping("testTriangleBianjie")
    public Map<String, Object> testTriangleBianJie() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = triangleDriver.simpleRead("TriangleBianjie.xlsx");
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "三角形等价类测试")
    @GetMapping("testTriangleDengjia")
    public Map<String, Object> testTriangleDengJia() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = triangleDriver.simpleRead("TriangleDengjia.xlsx");
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "三角形边界值测试结果测试下载")
    @GetMapping("downloadTriangle")
    public Map<String, Object> downloadTriangle(HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = triangleDriver.simpleRead("TriangleBianjie.xlsx");
        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }

    @ApiOperation(value = "三角形等价类测试结果测试下载")
    @GetMapping("downloadTriangleDengjia")
    public Map<String, Object> downloadTriangleDengjia(HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<String>> bankListByExcel = triangleDriver.simpleRead("TriangleDengjia.xlsx");
        DownloadExcelUtil downloadExcelUtil=new DownloadExcelUtil();
        downloadExcelUtil.doGet(bankListByExcel, response);
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
