package com.huaqi.zhanxin.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class ExcelFormatUtil {


    /**
     * 读取excel表
     *
     * @param
     * @param
     * @return Workbook work = new XSSFWorkbook("D://GoolgeDownload//Summary.xlsx");
     * @throws Exception
     */
    public static List<List<Object>> getBankListByExcel() throws IOException {
        List<List<Object>> list = new ArrayList<List<Object>>();             // 读取的数据放入该集合中
        String local_path = System.getProperty("user.dir");
        String path = local_path+"/backEnd/huaqi/src/main/resources/file/TriangleBianjie.xlsx";
        System.out.println(path);
        File file=new File(path);
        FileInputStream fileInputStream=new FileInputStream(file);
        XSSFWorkbook book = new XSSFWorkbook(fileInputStream);        // 文件所在位置
        XSSFSheet sheet = book.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            List<Object> list1 = new ArrayList<>();
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                System.out.println(row.getCell(0));
                if (row.getCell(0) != null) {        // 获取特殊格式的字段，如：改字段的属性为数字
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);            // 将改字段的属性设置为string类型
                }
                if (row.getCell(3) != null) {        // 获取特殊格式的字段，如：改字段的属性为数字
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);            // 将改字段的属性设置为string类型
                }
                String id = row.getCell(0).getStringCellValue();  // id		// 这边为读取的字段名称 ，写自己的就好
                String testUserName = row.getCell(1).getStringCellValue();  // 测试员
                String description = row.getCell(2).getStringCellValue();    // 描述
                String version = row.getCell(3).getStringCellValue();  // 版本号		// 这边为读取的字段名称 ，写自己的就好
                String testInput = row.getCell(4).getStringCellValue();  // 测试输入
                String testGoal = row.getCell(5).getStringCellValue();    // 测试目标
                String judgePass = row.getCell(6).getStringCellValue();  // 是否通过		// 这边为读取的字段名称 ，写自己的就好
                String preTestCondition = row.getCell(7).getStringCellValue();  // 在执行测试用例之前，应满足其执行的前提条件
                String realOutput = row.getCell(8).getStringCellValue();    // 实际输出
                String predictOutput = row.getCell(9).getStringCellValue();    // 预计输出
                Timestamp testTime = new Timestamp(System.currentTimeMillis());

                list1.add(id);            // 将读取的数据放入集合
                list1.add(testUserName);
                list1.add(description);
                list1.add(version);
                list1.add(testInput);
                list1.add(testGoal);
                list1.add(judgePass);
                list1.add(preTestCondition);
                list1.add(realOutput);
                list1.add(predictOutput);
                list1.add(testTime);
                list.add(list1);
            }
        }
        return list;
    }

    public static List<List<Object>> readCalendarBorder() throws IOException {
        List<List<Object>> list = new ArrayList<List<Object>>();             // 读取的数据放入该集合中
        String local_path = System.getProperty("user.dir");
        String path = local_path+"/backEnd/huaqi/src/main/resources/file/calendar_border.xlsx";
        File file=new File(path);
        System.out.println(path);
        FileInputStream fileInputStream=new FileInputStream(file);
        XSSFWorkbook calendarBorder = new XSSFWorkbook(fileInputStream);        // 文件所在位置
        XSSFSheet sheet = calendarBorder.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            List<Object> list1 = new ArrayList<>();
            XSSFRow row = sheet.getRow(i);
            if (row.getCell(0) != null) {
                System.out.println(row.getCell(0));
                if (row.getCell(0) != null) {        // 获取特殊格式的字段，如：改字段的属性为数字
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);            // 将改字段的属性设置为string类型
                }
                else {
                    System.out.println("第一列为空");
                }
                if (row.getCell(3) != null) {        // 获取特殊格式的字段，如：改字段的属性为数字
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);            // 将改字段的属性设置为string类型
                }
                String id = row.getCell(0).getStringCellValue();  // id		// 这边为读取的字段名称 ，写自己的就好
                String testUserName = row.getCell(1).getStringCellValue();  // 测试员
                String description = row.getCell(2).getStringCellValue();    // 描述
                String version = row.getCell(3).getStringCellValue();  // 版本号		// 这边为读取的字段名称 ，写自己的就好
                String testInput = row.getCell(4).getStringCellValue();  // 测试输入
                String testGoal = row.getCell(5).getStringCellValue();    // 测试目标
                String judgePass = "";
                String preTestCondition = row.getCell(7).getStringCellValue();  // 在执行测试用例之前，应满足其执行的前提条件
                String realOutput = "";
                String predictOutput = row.getCell(9).getStringCellValue();    // 预计输出
                Timestamp testTime = new Timestamp(System.currentTimeMillis());
                list1.add(id);            // 将读取的数据放入集合
                list1.add(testUserName);
                list1.add(description);
                list1.add(version);
                list1.add(testInput);
                list1.add(testGoal);
                list1.add(judgePass);
                list1.add(preTestCondition);
                list1.add(realOutput);
                list1.add(predictOutput);
                list1.add(testTime);
                list.add(list1);
            }
        }
        return list;
    }
}

