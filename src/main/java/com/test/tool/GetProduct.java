package com.test.tool;

import com.test.model.Detailedinfo;
import com.test.model.SolrBean;
import com.test.service.TestService;
import com.test.solr.Query;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
@Component
public class GetProduct {
    @Autowired
    private TestService testService;

    public  void getProduct() throws Exception{
        Query query=new Query();
        List<Detailedinfo> productList = testService.getMfr();
        List<Detailedinfo> products=new ArrayList<>();
        for(Detailedinfo p:productList){
            SolrBean solrBean=query.querySolr(p.getObjectid());
            String price=solrBean.getPrice();
            String attr=solrBean.getAttr();
            p.setAttr_json(attr);
            p.setPrice_json(price);
            products.add(p);
        }
        getExcel("taiyo yuden",products);
    }

    public static void getExcel(String excelName, List<Detailedinfo> list) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        HSSFRow rowTitle = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        String strArray[] = {"mpn", "webmpn", "description", "detailed description", "spq", "category_first", "category_second", "leadtime", "img", "pdfs", "mfr", "packaging", "attr_json","stock","price"};
        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = rowTitle.createCell(i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            List<String> string = new ArrayList<>();
            string.add(0, list.get(i).getMpn());
            string.add(1, list.get(i).getWebmpn());
            string.add(2, list.get(i).getDescript());
            string.add(3, list.get(i).getDetailed());
            string.add(4, list.get(i).getSpq());
            string.add(5, list.get(i).getSecondary_classification());
            string.add(6, list.get(i).getThreelevel_classification());
            string.add(7, list.get(i).getLeadtime());
            string.add(8, list.get(i).getImg());
            string.add(9, list.get(i).getPdfs());
            string.add(10, list.get(i).getMfr());
            string.add(11, list.get(i).getPackaging());
            string.add(12, list.get(i).getAttr_json());
            string.add(13, String.valueOf(list.get(i).getStock()));
            string.add(14, list.get(i).getPrice_json());
            for (int j = 0; j < strArray.length; j++) {
                row.createCell(j).setCellValue(string.get(j));
            }
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\" + excelName + ".xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
