package com.huaqi.zhanxin.tools;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class DownloadExcelUtil {
    public void doGet(List<List<String>> testList,HttpServletResponse response)
            throws ServletException, IOException {
//        StringBuilder jsonstr = new StringBuilder();//转化成json
//        jsonstr.append("{");
//        String reqstr = request.getQueryString();//获取请求参数
//        if(!"".equals(reqstr) &&reqstr !=null){
//
//            String [] stringArr= reqstr.split("&");
//            for(int i = 0;i<stringArr.length;i++){
//                String [] strArr = stringArr[i].split("=");
//
//                jsonstr.append("\""+strArr[0]+"\":"+"\""+strArr[1]+"\""+",") ;
//            }
//            jsonstr.deleteCharAt(jsonstr.length()-1);
//        }
//        jsonstr.append("}");
        String filename =getExcelPath() ;
//        List<List<String>> searchlist = testList;

        if(testList!=null){
            HSSFWorkbook wb = createExcel(testList);
            try{ //写入浏览器
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                OutputStream outputStream=response.getOutputStream();
                wb.write(outputStream);
                outputStream.flush();
                outputStream.close();
            }catch(Exception e){
                System.out.println("错误");
//                logger.error("Exception",e);
            }
        }
    }

    /**
     *  返回excel表生成的路径
     *  参数
     *  os,devicestatue，peopleId
     * @return excel表的服务器存储地址
     * */
    public String getExcelPath() {
        //String str = this.getServletContext().getRealPath("/WEB-INF");
        List<Map<String, Object>> searchlist = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String timestr = df.format(new Date()).toString().replace(" ", "-");
        timestr = timestr.replace(":","");
        String filename = "测试报告-"+ timestr+".xls";
        System.out.println("下载:"+filename);
        return filename;
    }

    /**
     *  创建excel表
     *  参数
     *  数据库查询数据list，存储路径
     * @return boolean true:生成成功  false: 生成失败
     * @throws
     * */
    public HSSFWorkbook createExcel(List<List<String>> testList) {

        boolean bool = true;
        // 第一步，创建一个webbook，对应一个Excel文件
        //XSSFWorkbook  wb = new XSSFWorkbook();
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("测试报告");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        ArrayList<String> title = new ArrayList<>(Arrays.asList("用例编号","测试用例执行人员","测试问题的描述","被测试程序的版本号","输入(合理的、不合理的)","测试用例的目的","是否通过","在执行测试用例之前，应满足其执行的前提条件","实际输出","预期输出(合理的及不合理的异常等)","测试用例执行时间"));
        //  System.out.println(title.size());
        HSSFCell cell;
        for(int i = 0 ;i<title.size();i++){
            cell = row.createCell((short) i);
            cell.setCellValue(title.get(i));
        }
        HSSFRow row2;
        HSSFCell cell2;
        int i = 0;
        int j = 0;
        for (List<String> list : testList) {//填充数据
            row2 = sheet.createRow((int) i+1);
            List<String> datalist = list;
            for( j = 0 ;j<datalist.size();j++){
                cell2 = row2.createCell((short) j);
                cell2.setCellValue(datalist.get(j));
            }
            i++;
        }


        return wb;
    }

    /**
     *  处理数据库查询出的数据，按照excel 列的顺序
     *  参数
     *  Map<String , Object>一行数据
     * @return datalist
     * */
//    public List<String> getData(List<Object> list){
//        List<String> stringList = new ArrayList<String>();
//        for(Map.Entry<String,Object> entry : map.entrySet()){
//            if(entry.getValue() == null){
//                entry.setValue("");
//            }
//            if(entry.getValue() instanceof Boolean){//false:0 true:1
//                if((Boolean)entry.getValue()){
//                    entry.setValue(1);
//                }
//                else {
//                    entry.setValue(0);
//                }
//            }
//        }
//        stringList.add(map.get("deviceid").toString());
//        stringList.add(map.get("tag").toString());
//        stringList.add(map.get("model").toString());
//        stringList.add(map.get("brand").toString());
//        stringList.add(map.get("os").toString());
//        stringList.add(map.get("osversion").toString());
//        stringList.add(map.get("cpu").toString());
//        stringList.add(map.get("cpumodel").toString());
//        stringList.add(map.get("mem").toString());
//        stringList.add(map.get("screensize").toString());
//        stringList.add(map.get("resolution_high").toString());
//        stringList.add(map.get("resolution_width").toString());
//        stringList.add(map.get("imei1").toString());
//        stringList.add(map.get("imei2").toString());
//        stringList.add(map.get("sn").toString());
//        stringList.add(map.get("root").toString());
//        stringList.add(map.get("color").toString());
//        stringList.add(map.get("frontcam").toString());
//        stringList.add(map.get("backcam").toString());
//        stringList.add(map.get("usbcable").toString());
//        stringList.add(map.get("charger").toString());
//        stringList.add(map.get("headset").toString());
//        stringList.add(map.get("battery").toString());
//        stringList.add(map.get("phoneshell").toString());
//        stringList.add(map.get("adddate").toString());
//        stringList.add(map.get("managername").toString());
//        stringList.add(map.get("name").toString());
//        stringList.add(map.get("devicestatus").toString());
//        stringList.add(map.get("devicenotes").toString());
//
//        return stringList;
//
//    }
}
