package com.huaqi.zhanxin.controller;

import com.huaqi.zhanxin.entity.*;
import com.huaqi.zhanxin.service.triangleDriver;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "三角形测试")
    @GetMapping("testTriangle")
    public Map<String, Object> getUserName() throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 读取excel表格
        List<List<Object>> bankListByExcel = triangleDriver.simpleRead();
        map.put("data", bankListByExcel);
        helper.setMsg("Success");
        helper.setData(map);
        return helper.toJsonMap();
    }
}
