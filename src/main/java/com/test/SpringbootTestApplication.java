package com.test;


import com.test.tool.GetProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootTestApplication {
//    @Autowired
//    private static UploadProduct uploadProduct;
      @Autowired
    private static GetProduct getProduct;

    public static void main(String[] args) throws Exception {
//        uploadProduct=SpringApplication.run(SpringbootTestApplication.class, args).getBean(UploadProduct.class);
//        //ThreadPool.init();
//        uploadProduct.upload();

        getProduct=SpringApplication.run(SpringbootTestApplication.class, args).getBean(GetProduct.class);
        getProduct.getProduct();
    }

}
