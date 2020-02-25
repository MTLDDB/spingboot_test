package com.test.spiderByMfr;

import com.test.tool.ExcelData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    public List<String> getExcel(String path) {
        List<String> urlList = new ArrayList<>();
        ExcelData excelData = new ExcelData(path, "sheet1");
        HSSFSheet sheet = excelData.getSheet();
        int rows = sheet.getPhysicalNumberOfRows();//行数
        for (int i = 1; i < rows; i++) {
            //获取列数
            HSSFRow row = sheet.getRow(i);
            //int columns = row.getPhysicalNumberOfCells();//列数
            String cell = row.getCell(0).toString();
            urlList.add(cell);
        }
        return urlList;
    }
}
