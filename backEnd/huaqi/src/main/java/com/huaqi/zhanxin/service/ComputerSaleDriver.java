package com.huaqi.zhanxin.service;

import com.huaqi.zhanxin.tools.ExcelFormatUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ComputerSaleDriver {
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
            String result = computerSale(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            list.set(8, result);
            list.set(6, "是");
        }
        return bankListByExcel;
    }

    public String computerSale( int host, int monitor, int peripheral ) {
        int flag, sale;
        double brokerage=0;
        // 先检查输入是否合法
        if (host<1 || monitor<1 || peripheral<1) {
            flag=0;
            return "需销售一台完整的机器才能提取佣金！";
        }
        else if (host>70 || monitor>80 || peripheral>90) {
            flag=0;
            return "销售数量超过最大限额！";
        }
        else {
            sale=host*25+monitor*30+peripheral*45;
            flag=-1;
        }
        if (flag==-1) {
            if (sale<=1000) {
                brokerage=sale*0.1;
            }
            else if (sale<=1800) {
                brokerage=sale*0.15;
            }
            else {
                brokerage=sale*0.2;
            }
        }
        return "销售额："+sale+" 佣金："+brokerage;
    }
}
