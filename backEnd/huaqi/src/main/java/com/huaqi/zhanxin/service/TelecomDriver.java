package com.huaqi.zhanxin.service;

import org.springframework.stereotype.Service;

@Service
public class TelecomDriver {
    public String telecomFee( int phoneMinute, int lateCostTime ) {
        double discount=1, phoneFee, fee;
        if (phoneMinute<0||lateCostTime<0) {
            return "输入需为正整数！";
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
