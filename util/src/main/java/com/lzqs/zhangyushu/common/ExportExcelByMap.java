package com.lzqs.zhangyushu.common;


import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExportExcelByMap {
    /**
     * 这是一个通用的方法，
     *
     * @param excelData 需要显示的数据集合,集合中一定要放置符合Map风格的类的对象。此方法支持的
     *                  javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @throws Exception
     */


    public UrlCon exportExcels(List<Map<String, Object>> excelData, String exportName, FileCon fileCon) throws Exception {
        UrlCon urlCon = new UrlCon();

        if (excelData.size() > 0) {

            //* @param sheetName    表格sheet名
            String url = fileCon.getPath() + exportName + ".xls";


            urlCon.setFilePath(url);

            urlCon.setPath(fileCon.getExcelFile() + exportName + ".xls");

            //文件在服务器的存储位置
            try {
                // out与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
                FileOutputStream out = new FileOutputStream(new File(url));
                //列名 key值数组
                List<Object> headers = new ArrayList<>();
                headers.add("学号");
                headers.add("姓名");
                headers.add("性别");
                headers.add("授课老师");
                headers.add("班号");
                headers.add("状态");
                headers.add("总分");
//                headers.add("时间");

                //pattern如果有时间数据，设定输出格式。
                String pattern = "yyyy-MM-dd'T'HH:mm:ssX";

                // 声明一个工作薄
                XSSFWorkbook workbook = new XSSFWorkbook();

                int iMaxLines = 65534;
                // 生成一个表格
                int index = 0;
                int page = 1;
                XSSFSheet sheet = workbook.createSheet(exportName);
                // 设置表格默认列宽度为15个字节
                sheet.setDefaultColumnWidth((int) 15);
                // 产生表格标题行
                XSSFRow row = sheet.createRow(0);

                //判定生成哪个excel接口的表头
                for (int i = 0; i < headers.size(); i++) {
                    XSSFCell cell = row.createCell(i);
                    XSSFRichTextString text = new XSSFRichTextString((String) headers.get(i));
                    cell.setCellValue(text);
                }
                // 遍历集合数据，产生数据行
                Iterator<Map<String, Object>> it = excelData.iterator();
                while (it.hasNext()) {
                    index++;

                    row = sheet.createRow(index);

                    Map<String, Object> t = it.next();

                    int m = 0;
                    for (short n = 0; n < headers.size(); n++) {
                        if (n == 0) m = 0;
                        XSSFCell cell = row.createCell(m);
                        System.out.println(cell);
                        m++;

                        Object value = t.get(headers.get(n));
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value == null ? null : value.toString();
                        }
//                 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
//                    Pattern p = Pattern.compile("^\\d+(\\.\\d+)?{1}");
                            Pattern p = Pattern.compile("^[0-9]*$ ");
                            Pattern p1 = Pattern.compile("^\\d+$");
                            Matcher matcher = p.matcher(textValue);
                            Matcher matcher1 = p1.matcher(textValue);
                            if (matcher.matches()) {
                                double v = Double.parseDouble(textValue);
                                cell.setCellValue(0.00);
                                // 是数字当作double处理
                                cell.setCellValue(v);
                            } else {
                                if (matcher1.matches()) {
                                    long l = Long.parseLong(textValue);
                                    cell.setCellValue(l);
                                } else {
                                    cell.setCellValue(textValue);
                                }
                            }
                        }
                    }
                }
                workbook.write(out);
                out.flush();
                out.close();
                urlCon.setStatus(1);
            } catch (Exception e) {
                e.printStackTrace();
                urlCon.setStatus(2);
                return urlCon;
            }
        }
        return urlCon;
    }





    public UrlCon exportExcelsGradeQuestions(List<Map<String, Object>> excelData, String exportName, FileCon fileCon) throws Exception {
        UrlCon urlCon = new UrlCon();

        if (excelData.size() > 0) {

            //* @param sheetName    表格sheet名
            String url = fileCon.getPath() + exportName + ".xls";


            urlCon.setFilePath(url);

            urlCon.setPath(fileCon.getExcelFile() + exportName + ".xls");

            //文件在服务器的存储位置
            try {
                // out与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
                FileOutputStream out = new FileOutputStream(new File(url));
                //列名 key值数组
                List<Object> headers = new ArrayList<>();
                headers.add("学号");
                headers.add("姓名");
                headers.add("性别");
                headers.add("授课老师");
                headers.add("班号");
                headers.add("状态");
                headers.add("总分");
//                headers.add("时间");

                //pattern如果有时间数据，设定输出格式。
                String pattern = "yyyy-MM-dd'T'HH:mm:ssX";

                // 声明一个工作薄
                XSSFWorkbook workbook = new XSSFWorkbook();

                int iMaxLines = 65534;
                // 生成一个表格
                int index = 0;
                int page = 1;
                XSSFSheet sheet = workbook.createSheet(exportName);
                // 设置表格默认列宽度为15个字节
                sheet.setDefaultColumnWidth((int) 15);
                // 产生表格标题行
                XSSFRow row = sheet.createRow(0);

                //判定生成哪个excel接口的表头
                for (int i = 0; i < headers.size(); i++) {
                    XSSFCell cell = row.createCell(i);
                    XSSFRichTextString text = new XSSFRichTextString((String) headers.get(i));
                    cell.setCellValue(text);
                }
                // 遍历集合数据，产生数据行
                Iterator<Map<String, Object>> it = excelData.iterator();
                while (it.hasNext()) {
                    index++;

                    row = sheet.createRow(index);

                    Map<String, Object> t = it.next();

                    int m = 0;
                    for (short n = 0; n < headers.size(); n++) {
                        if (n == 0) m = 0;
                        XSSFCell cell = row.createCell(m);
                        System.out.println(cell);
                        m++;

                        Object value = t.get(headers.get(n));
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value == null ? null : value.toString();
                        }
//                 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
//                    Pattern p = Pattern.compile("^\\d+(\\.\\d+)?{1}");
                            Pattern p = Pattern.compile("^[0-9]*$ ");
                            Pattern p1 = Pattern.compile("^\\d+$");
                            Matcher matcher = p.matcher(textValue);
                            Matcher matcher1 = p1.matcher(textValue);
                            if (matcher.matches()) {
                                double v = Double.parseDouble(textValue);
                                cell.setCellValue(0.00);
                                // 是数字当作double处理
                                cell.setCellValue(v);
                            } else {
                                if (matcher1.matches()) {
                                    long l = Long.parseLong(textValue);
                                    cell.setCellValue(l);
                                } else {
                                    cell.setCellValue(textValue);
                                }
                            }
                        }
                    }
                }
                workbook.write(out);
                out.flush();
                out.close();
                urlCon.setStatus(1);
            } catch (Exception e) {
                e.printStackTrace();
                urlCon.setStatus(2);
                return urlCon;
            }
        }
        return urlCon;
    }

}