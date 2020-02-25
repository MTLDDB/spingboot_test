package com.test;

import com.alibaba.fastjson.JSON;
import com.test.model.Detailedinfo;
import com.test.model.Task;
import com.test.pool.ThreadPool;
import com.test.service.QueryService;
import com.test.service.TaskService;
import com.test.service.TestService;
import com.test.solr.UploadProduct;
import org.apache.poi.hssf.usermodel.*;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootTestApplicationTests {
    @Autowired
    private TestService testService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private QueryService queryService;
    @Autowired
    private UploadProduct uploadProduct;

    @Test
    void contextLoads() throws IOException, SolrServerException {
//
        //ThreadPool.init();
//        System.out.println(JSON.toJSONString(taskService.getTaskList(0,1))+JSON.toJSONString(taskService.getTaskList(1,1)));
//        System.out.println(JSON.toJSONString(taskService.getTaskList(0,2)));
        //System.out.println(queryService.getTaskListProOne());
        // System.out.println(JSON.toJSONString(queryService.getTaskListPro(queryService.getTaskListProOne(),10)));
        // uploadProduct.upload();

//        List<Detailedinfo> ttMytaskList=testService.getProductBy("webmpn","CTVPS00RF-25-24SC-ND");
//        System.out.println(JSON.toJSONString(ttMytaskList));
        //  List<Detailedinfo> ttMytaskList=testService.getMfr();
        //   List<String> list=getString("C:\\Users\\PC\\Desktop\\新建文本文档 (5).txt");
//        for(String str:list){
//            String json= JSON.toJSONString(JSONObject.parseObject(str,JSONObject.class));
//            Detailedinfo detailedinfo=JSONObject.parseObject(json,Detailedinfo.class);
//            List<Task> ttMytaskList=testService.getUrl("objectid",detailedinfo.getObjectid());
//            System.out.println(JSON.toJSONString(ttMytaskList));
//            //testService.update(detailedinfo.getObjectid());
//           //// testService.delete(detailedinfo.getObjectid());
//           // String attr_json=testService.getAttr(detailedinfo.getObjectid());
//          //  System.out.println(attr_json);
//          //  detailedinfo.setAttr_json(attr_json);
//        }

        List<Detailedinfo> productList = testService.getMfr();
        List<Detailedinfo> products=new ArrayList<>();
        for(Detailedinfo p:productList){
            String attr=testService.getAttr(p.getObjectid());
            p.setAttr_json(attr);
            products.add(p);
        }
        getExcel("Rubycon", products);
        // System.out.println(ttMytaskList.size());
        // System.out.println(JSON.toJSONString(ttMytaskList));
//
//        String attr=testService.getAttr("b2744f248f223f02a4d92aa36d1bd274");
//        System.out.println(attr);


        // testService.insert("59b8c3ed2e064cb3982c24af63619ef5","30608","d10db22b95e73c3593867213ba46e569");
        /*List<Stock> stockList=testService.get();
        String json= JSON.toJSONString(stockList);
        System.out.println(json);*/
        //// testService.deleteAll();//用于删除表中的数据
        //  System.out.println(testService.getTask().size());
//        for(Task task:testService.getTask()){
//            System.out.println(JSON.toJSONString(task.getUrl()));
//        }
        //  System.out.println(JSON.toJSONString(testService.getUrl("url","https://www.digikey.com/product-detail/en/amphenol-aerospace-operations/CTVPS00RF-25-24SC/CTVPS00RF-25-24SC-ND/5480299")));
        // System.out.println(JSON.toJSONString(testService.getTask1("tt_mytask")));
//        Task  ttMytask=new Task();
//        List<Task> ttMytaskList=testService.getUrl("url",
//                "https://www.digikey.com/product-detail/en/samtec-inc/DW-50-11-L-T-400/DW-50-11-L-T-400-ND/6776277");
//        System.out.println(JSON.toJSONString(ttMytaskList));

        // System.out.println(testService.getIdbyWebmpn("ESC26DREN"));
        // List<Price> prices=testService.getPrice("3cd0167e2dcdbba678da39f1efadda09");
        // System.out.println(JSON.toJSONString(prices));

        /*  stockList=testService.get();
        json= JSON.toJSONString(stockList);
        System.out.println(json);*/
       /* for(Stock s:stockList){
            if(s.getDetail_id().equals("59b8c3ed2e064cb3982c24af63619ef5"))
                break;
            testService.delete(s.getObjectid());
        }*/
    }

    public static List<String> getString(String path) {
        //"C:\\Users\\PC\\Desktop\\log\\localhost_access_log.2019-11-02.txt"
        File file = new File(path);
        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linetxt = null;
            //result用来存储文件内容
            StringBuilder result = new StringBuilder();
            //按使用readLine方法，一次读一行
            while ((linetxt = bufferedReader.readLine()) != null) {
                String[] re = linetxt.split("\t");
                for (int i = 0; i < re.length; i++) {
                    //if (re[i].contains(".pdf")) {
                    //   System.out.println(re[i]);
                    list.add(re[i]);
                    // }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void getExcel(String excelName, List<Detailedinfo> list) {
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
        //"descript":"NOR FLASH",
        // "detailed":"FLASH - NOR Memory IC 16Mb (2M x 8) SPI - Quad I/O 104MHz 8-SOP",
        // "domain":"digikey.com",
        // "factory_stock":0,
        // "first_classification":"",
        // "img":"[\"https://media.digikey.com/Renders/~~Pkg.Case%20or%20Series/8-SOIC.jpg\"]",
        // "leadtime":"4 Weeks","moq":"1",
        // "mpn":"GD25LQ16CSIGR",
        //"packaging":"Cut Tape (CT)",
        // "pdfs":"{\"GD25LQ16C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25lq16c/\",
        // \"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}",
        // "secondary_classification":"Integrated Circuits (ICs)",
        // "stock":3352,
        // "threelevel_classification":"Memory",
        // "url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25LQ16CSIGR/1970-1029-1-ND/9484779","webmpn":"1970-1029-1-ND"},
        // {"attr_json":"{\"Clock Frequency\":\"133MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"21-WLCSP \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"2.4ms \",\"Memory Size\":\"64Mb (8M x 8) \",\"Package / Case\":\"21-XFBGA, WLSCP \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"1.65V ~ 2V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 64Mb (8M x 8) SPI - Quad I/O 133MHz 21-WLCSP","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"\"]","leadtime":"4 Weeks","moq":"6000","mpn":"GD25LE64CLIGR","objectid":"1cb1cafd7cdd35aab0d706393c28973b","packaging":"Tape & Reel (TR)",
        // "pdfs":"{\"GD25LE64C\":\"http://www.gigadevice.com/datasheet/gd25le64c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":0,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25LE64CLIGR/GD25LE64CLIGR-ND/9484992","webmpn":"GD25LE64CLIGR-ND"},{"attr_json":"{\"Clock Frequency\":\"120MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-USON (4x3) \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 2.4ms \",\"Memory Size\":\"32Mb (4M x 8) \",\"Package / Case\":\"8-UDFN Exposed Pad \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.7V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Digi-Reel® \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 32Mb (4M x 8) SPI - Quad I/O 120MHz 8-USON (4x3)","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/Renders/Winbond%20Renders/256;8USON-.60-4x3;UU;8.jpg\"]","leadtime":"4 Weeks","moq":"1","mpn":"GD25Q32CNIGR","objectid":"2330d545451f387db74dd3f402261889","packaging":"Digi-Reel®","pdfs":"{\"GD25Q32C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25q32c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":3538,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25Q32CNIGR/1970-1033-6-ND/9484866","webmpn":"1970-1033-6-ND"},{"attr_json":"{\"Clock Frequency\":\"104MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-USON (2x3) \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"- \",\"Memory Size\":\"2Mb (256K x 8) \",\"Package / Case\":\"8-XFDFN Exposed Pad \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.1V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Discontinued at Digi-Key \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 2Mb (256K x 8) SPI - Quad I/O 104MHz 8-USON (2x3)","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/renders/GigaDevice%20Renders/1970;8USON-.50-2x3;E;8.jpg\"]","leadtime":"","moq":"3000","mpn":"GD25VE20CEIGR","objectid":"47ca6fc1ac4339e6b8eca1053c6a9bbd","packaging":"Tape & Reel (TR)","pdfs":"{\"GD25VE20C\":\"http://www.gigadevice.com/datasheet/gd25ve20c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":0,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25VE20CEIGR/1970-1060-2-ND/9484725","webmpn":"1970-1060-2-ND"},{"attr_json":"{\"Clock Frequency\":\"120MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-USON (2x3) \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 2.4ms \",\"Memory Size\":\"8Mb (1M x 8) \",\"Package / Case\":\"8-XFDFN Exposed Pad \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.7V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Cut Tape (CT) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 8Mb (1M x 8) SPI - Quad I/O 120MHz 8-USON (2x3)","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/renders/GigaDevice%20Renders/1970;8USON-.50-2x3;E;8.jpg\"]","leadtime":"4 Weeks","moq":"1","mpn":"GD25Q80CEIGR","objectid":"55df3761eed331039447493f8240b258","packaging":"Cut Tape (CT)","pdfs":"{\"GD25Q80C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25q80c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":5138,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25Q80CEIGR/1970-1009-1-ND/9484759","webmpn":"1970-1009-1-ND"},{"attr_json":"{\"Clock Frequency\":\"120MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-SOP \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 2.4ms \",\"Memory Size\":\"8Mb (1M x 8) \",\"Package / Case\":\"8-SOIC (0.209\\\", 5.30mm Width) \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.7V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tube \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 8Mb (1M x 8) SPI - Quad I/O 120MHz 8-SOP","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/Renders/~~Pkg.Case%20or%20Series/8-SOIC.jpg\"]","leadtime":"4 Weeks","moq":"19000","mpn":"GD25Q80CSIG","objectid":"5ce0599fa0c9303c9577cee2c42ede85","packaging":"Tube","pdfs":"{\"GD25Q80C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25q80c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":0,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25Q80CSIG/GD25Q80CSIG-ND/9484923","webmpn":"GD25Q80CSIG-ND"},{"attr_json":"{\"Clock Frequency\":\"104MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-SOP \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 2.4ms \",\"Memory Size\":\"16Mb (2M x 8) \",\"Package / Case\":\"8-SOIC (0.154\\\", 3.90mm Width) \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"1.65V ~ 2.1V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 16Mb (2M x 8) SPI - Quad I/O 104MHz 8-SOP","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/Renders/~~Pkg.Case%20or%20Series/8-SOIC.jpg\"]","leadtime":"4 Weeks","moq":"3000","mpn":"GD25LQ16CTIGR","objectid":"6c445bd46f2f334a8858ca7bc5a66465","packaging":"Tape & Reel (TR)","pdfs":"{\"GD25LQ16C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25lq16c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":0,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25LQ16CTIGR/1970-1037-2-ND/9484702","webmpn":"1970-1037-2-ND"},{"attr_json":"{\"Clock Frequency\":\"120MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-WSON (5x6) \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"2.4ms \",\"Memory Size\":\"64Mb (8M x 8) \",\"Package / Case\":\"8-WDFN Exposed Pad \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"1.65V ~ 2V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 64Mb (8M x 8) SPI - Quad I/O 120MHz 8-WSON (5x6)","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/renders/GigaDevice%20Renders/1970;8WSON-.80-5x6;W;8.jpg\"]","leadtime":"4 Weeks","moq":"3000","mpn":"GD25LQ64CWIGR","objectid":"796128acf2403cd6adce98e96c2750cb","packaging":"Tape & Reel (TR)","pdfs":"{\"GD25LQ64C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25lq64c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":3000,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25LQ64CWIGR/1970-1054-2-ND/9484719","webmpn":"1970-1054-2-ND"},{"attr_json":"{\"Clock Frequency\":\"104MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-USON (2x3) \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 2.4ms \",\"Memory Size\":\"1Mb (128K x 8) \",\"Package / Case\":\"8-XFDFN Exposed Pad \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"1.65V ~ 2.1V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 1Mb (128K x 8) SPI - Quad I/O 104MHz 8-USON (2x3)","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/renders/GigaDevice%20Renders/1970;8USON-.50-2x3;E;8.jpg\"]","leadtime":"4 Weeks","moq":"3000","mpn":"GD25LQ10CEIGR","objectid":"7e3701a81ef033a28e72b928e9a42573","packaging":"Tape & Reel (TR)","pdfs":"{\"GD25LQxxC Datasheet\":\"http://www.gigadevice.com/datasheet/gd25lq05c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":3000,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25LQ10CEIGR/1970-1016-2-ND/9484681","webmpn":"1970-1016-2-ND"},{"attr_json":"{\"Clock Frequency\":\"104MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-SOP \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"12µs, 2.4ms \",\"Memory Size\":\"128Mb (16M x 8) \",\"Package / Case\":\"8-SOIC (0.209\\\", 5.30mm Width) \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.7V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 128Mb (16M x 8) SPI - Quad I/O 104MHz 8-SOP","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/Renders/~~Pkg.Case%20or%20Series/8-SOIC.jpg\"]","leadtime":"4 Weeks","moq":"2000","mpn":"GD25Q127CSIGR","objectid":"8b1676da84eb3568881fd5f67915e837","packaging":"Tape & Reel (TR)","pdfs":"{\"GD25Q127C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25q127c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":2000,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25Q127CSIGR/1970-1027-2-ND/9484692","webmpn":"1970-1027-2-ND"},{"attr_json":"{\"Clock Frequency\":\"104MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-SOP \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 2.4ms \",\"Memory Size\":\"4Mb (512K x 8) \",\"Package / Case\":\"8-SOIC (0.209\\\", 5.30mm Width) \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.7V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Digi-Reel® \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 4Mb (512K x 8) SPI - Quad I/O 104MHz 8-SOP","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/Renders/~~Pkg.Case%20or%20Series/8-SOIC.jpg\"]","leadtime":"4 Weeks","moq":"1","mpn":"GD25Q40CSIGR","objectid":"9a401b52113c3c49b76d778fc06a7937","packaging":"Digi-Reel®","pdfs":"{\"GD25Q40C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25q40c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":2092,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25Q40CSIGR/1970-1022-6-ND/9484855","webmpn":"1970-1022-6-ND"},{"attr_json":"{\"Clock Frequency\":\"104MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-SOP \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"50µs, 3ms \",\"Memory Size\":\"8Mb (1M x 8) \",\"Package / Case\":\"8-SOIC (0.209\\\", 5.30mm Width) \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"2.3V ~ 3.6V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Tape & Reel (TR) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 8Mb (1M x 8) SPI - Quad I/O 104MHz 8-SOP","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/Renders/~~Pkg.Case%20or%20Series/8-SOIC.jpg\"]","leadtime":"6 Weeks","moq":"10000","mpn":"GD25VQ80CSIGR","objectid":"9c66f11f377338fa8ca1c0c71a49b62d","packaging":"Tape & Reel (TR)","pdfs":"{\"GD25VQ80C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25vq80c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":0,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25VQ80CSIGR/GD25VQ80CSIGR-ND/9485012","webmpn":"GD25VQ80CSIGR-ND"},{"attr_json":"{\"Clock Frequency\":\"120MHz \",\"Operating Temperature\":\"-40°C ~ 85°C (TA) \",\"Memory Interface\":\"SPI - Quad I/O \",\"Categories\":\"Integrated Circuits (ICs) ->Memory \",\"Mounting Type\":\"Surface Mount \",\"Memory Type\":\"Non-Volatile \",\"Supplier Device Package\":\"8-WSON (5x6) \",\"Series\":\"- \",\"Write Cycle Time - Word, Page\":\"2.4ms \",\"Memory Size\":\"64Mb (8M x 8) \",\"Package / Case\":\"8-WDFN Exposed Pad \",\"Technology\":\"FLASH - NOR \",\"Voltage - Supply\":\"1.65V ~ 2V \",\"Manufacturer\":\"GigaDevice Semiconductor (HK) Limited \",\"Packaging\":\"Cut Tape (CT) \",\"Part Status\":\"Active \",\"Memory Format\":\"FLASH \"}","descript":"NOR FLASH","detailed":"FLASH - NOR Memory IC 64Mb (8M x 8) SPI - Quad I/O 120MHz 8-WSON (5x6)","domain":"digikey.com","factory_stock":0,"first_classification":"","img":"[\"https://media.digikey.com/renders/GigaDevice%20Renders/1970;8WSON-.80-5x6;W;8.jpg\"]","leadtime":"4 Weeks","moq":"1","mpn":"GD25LQ64CWIGR","objectid":"9ddc9bfa952d3117a0f63cedb25be95e","packaging":"Cut Tape (CT)","pdfs":"{\"GD25LQ64C Datasheet\":\"http://www.gigadevice.com/datasheet/gd25lq64c/\",\"Product Selection Guide\":\"https://www.gigadevice.com/wp-content/uploads/2019/06/GigaDevice-Product-Selection-Guide.pdf\"}","secondary_classification":"Integrated Circuits (ICs)","stock":4707,"threelevel_classification":"Memory","url":"https://www.digikey.com/product-detail/en/gigadevice-semiconductor-hk-limited/GD25LQ64CWIGR/1970-1054-1-ND/9484803","webmpn":"1970-1054-1-ND"}
        String strArray[] = {"mpn", "webmpn", "description", "detailed description", "spq", "category_first", "category_second", "leadtime", "img", "pdfs", "mfr", "packaging", "attr_json","stock"};

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
