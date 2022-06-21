package com.huaqi.zhanxin.entity;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TriangleExcelEntity {
    private Integer id;
    private String testUserName;
    private String description;
    private String version;
    private String testInput;
    private String testGoal;
    private String judgePass;
    private String preTestCondition;
    private String realOutput;
    private String predictOutput;
    private Timestamp testTime;
}
