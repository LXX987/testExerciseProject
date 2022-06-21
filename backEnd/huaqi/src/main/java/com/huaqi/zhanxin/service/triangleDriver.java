package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.entity.TriangleExcelEntity;
import com.huaqi.zhanxin.tools.ExcelFormatUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class triangleDriver {

    // 上传csv
    public List<List<Object>> simpleRead() throws IOException {
        List<List<Object>> bankListByExcel = ExcelFormatUtil.getBankListByExcel();
        // 获取集合对象
        for (List<Object> list : bankListByExcel) {
            //            TriangleExcelEntity triangleExcelEntity = new TriangleExcelEntity();    // 开始封装对象
//            triangleExcelEntity.setId(Integer.parseInt(list.get(0).toString()));
//            triangleExcelEntity.setTestUserName(list.get(1).toString());
            // 拿实验数据
            String testInput = list.get(4).toString();
            String[] temp = testInput.split(",");
            // 开始测试
            String result = testTriangle(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            list.set(8, result);
            list.set(6, "是");
        }
        return bankListByExcel;
    }

    public String testTriangle(int a, int b, int c) {
        // 先检查输入是否合法
        if(a>=1&&a<=100&&b>=1&&b<=100&&c>=1&&c<=100) {
            System.out.println("输入在范围内");
        } else {
            return "输入不合法";
        }

        // 开始判断三角形形状
        if(a+b>c&&b+c>a&&c+a>b) {
            System.out.println("构成三角形");
            if (a == b && b == c) {
                System.out.println("等边三角形");
                return "等边三角形";
            } else if (a == b || b == c || c == a) {
                System.out.println("等腰三角形");
                return "等腰三角形";
            } else {
                System.out.println("一般三角形");
                return "一般三角形";
            }
        } else {
            System.out.println("不构成三角形");
            return "不构成三角形";
        }
    }
}
