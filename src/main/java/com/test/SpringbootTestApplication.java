package com.test;

import com.test.pool.ThreadPool;
import com.test.solr.UploadProduct;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class SpringbootTestApplication {
    @Autowired
    private static UploadProduct uploadProduct;

    public static void main(String[] args)  throws IOException, SolrServerException {
        uploadProduct=SpringApplication.run(SpringbootTestApplication.class, args).getBean(UploadProduct.class);
        //ThreadPool.init();
        uploadProduct.upload();
    }

}
