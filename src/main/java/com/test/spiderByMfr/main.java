package com.test.spiderByMfr;

import com.test.mapper.TestKingShardMapper;
import com.test.model.Detailedinfo;
import com.test.service.TestService;
import com.test.solr.Query;
import org.apache.poi.hssf.usermodel.*;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

//    public static void main(String[] args) throws Exception {
//        new Query().querySolr("82fdea29512337718a19e590b1c1b60f");
//       // String url = "https://www.digikey.com/en/supplier-centers/t/taiyo-yuden";
//        //getCategoryList(url);//获取制造商下的所有的分类并生成Excel表，
//        //getListUrl();
//       // getEndUrl();
//    }



    public static void getEndUrl() {
        Fetch fetch = new Fetch();
        Paser paser = new Paser();
        ReadExcel readExcel = new ReadExcel();
        String path = "C:\\Users\\Administrator\\Desktop\\taiyo\\second3.xls";
        List<String> outList = new ArrayList<>();
        //for(int i=0;i<5;i++){
           // path=path+i+".xls";
            List<String> UrlList = readExcel.getExcel(path);
            for (String listUrl : UrlList) {
                try {
                    Document document = null;
                    while (document == null){
                        document=fetch.noProxyGetDoc(listUrl);
                    }
                    List<String> list=paser.getPageListUrl(document,listUrl);
                    outList.addAll(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            getExcel("end3",outList);
      //  }
    }
    public static void getListUrl() {
        Fetch fetch = new Fetch();
        Paser paser = new Paser();
        ReadExcel readExcel = new ReadExcel();
        String path = "C:\\Users\\Administrator\\Desktop\\taiyo\\tetsCate.xls";
        List<String> categoryUrlList = readExcel.getExcel(path);
        List<String> outList = new ArrayList<>();
        List<String> outUrl = new ArrayList<>();
        int i = 0;
        for (String listUrl : categoryUrlList) {
            try {
                Document document = null;
                System.out.println(listUrl);
                while (document == null)
                    document=fetch.noProxyGetDoc(listUrl);
                int flag=paser.judgeType(document);
                if (flag==1) {//如果该页码存在分页列表，直接将URL加入到excel
                    outList.add(listUrl);
                    System.out.println(flag);
                } else if(flag==2){
                    outUrl.add(listUrl);
                    System.out.println(flag);
                } else {//否者进行再分类
                    List<String> outList2 = paser.getListUrl(document);
                    getExcel("second" + i, outList2);
                    i++;
                }
                getExcel("thirdUrl", outUrl);
                getExcel("thirdList", outList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getCategoryList(String url) {
        try {
            Fetch fetch = new Fetch();
            Paser paser = new Paser();
            url = url.replace("|", "%7C").replace(",", "%2C");
            Document document = fetch.noProxyGetDoc(url);
            List<String> urlList = paser.getCategoryListUrl(document);
            getExcel("tetsCate", urlList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getExcel(String excelName, List<String> list) {
        //创建工作薄
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow rowTitle = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        String strArray[] = {"URL", "Domain", "Priority", "StockUpdate", "FilterUpdate"};
        // 添加excel title
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length; i++) {
            cell = rowTitle.createCell(i);
            cell.setCellValue(strArray[i]);
            cell.setCellStyle(style);
        }
        //  int k=0;

        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            List<String> string = new ArrayList<>();
            string.add(0, list.get(i));
            string.add(1, "digikey.com");
            string.add(2, "1");
            string.add(3, "0");
            string.add(4, "0");
            for (int j = 0; j < strArray.length; j++) {
                row.createCell(j).setCellValue(string.get(j));
            }
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\taiyo\\" + excelName + ".xls");
            wb.write(fout);
            fout.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
