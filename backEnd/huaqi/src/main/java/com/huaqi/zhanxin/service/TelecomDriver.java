package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.tools.ExcelFormatUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TelecomDriver {
    public List<List<String>> simpleRead() throws IOException {
        List<List<String>> bankListByExcel = ExcelFormatUtil.telecomFeeExcel();
        // 获取集合对象
        for (List<String> list : bankListByExcel) {
            //            TriangleExcelEntity triangleExcelEntity = new computerSaleExcel();    // 开始封装对象
//            triangleExcelEntity.setId(Integer.parseInt(list.get(0).toString()));
//            triangleExcelEntity.setTestUserName(list.get(1).toString());
            // 拿实验数据
            String testInput = list.get(4).toString();
            String[] temp = testInput.split(",");
            // 开始测试
            String result = telecomFee(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            list.set(8, result);
            list.set(6, "是");
        }
        return bankListByExcel;
    }
    public String telecomFee( int phoneMinute, int lateCostTime ) {
        double discount=1, phoneFee, fee;
        if (phoneMinute<0||lateCostTime<0) {
            return "输入需为正整数！";
        }
        else if (phoneMinute>44640||lateCostTime>11){
            return "输入超过限额！";
        }
        if (phoneMinute<=60) {
            if (lateCostTime<=1) {
                discount=0.99;
            }
        }
        else if (phoneMinute<=120) {
            if (lateCostTime<=2) {
                discount=0.985;
            }
        }
        else if (phoneMinute<=180) {
            if (lateCostTime<=3) {
                discount=0.98;
            }
        }
        else if (phoneMinute<=300) {
            if (lateCostTime<=3) {
                discount=0.975;
            }
        }
        else {
            if (lateCostTime<=6) {
                discount=0.97;
            }
        }
        phoneFee=phoneMinute*0.15;
        fee=25+phoneFee*discount;
        return "总费用："+fee;
    }
}
