//package com.test.solr;
//
//import com.alibaba.fastjson.JSON;
//import com.test.model.Detailedinfo;
//import com.test.service.QueryService;
//
//
//import org.apache.solr.client.solrj.SolrClient;
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocument;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class UploadProduct {
//    @Autowired
//    private  QueryService queryService;
//    @Autowired
//    private SolrClient solrClient;
//
//    public void upload() throws IOException, SolrServerException {
//        // HttpSolrClient solrServer = new HttpSolrClient.Builder(SOLR_URL +"spiderPro");
//        List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
//        SolrInputDocument doc = null;
//
//        int count = 0;
//        boolean flag = true;
//        String objectid ="0"; //queryService.getTaskListProOne();
//        //  List<Detailedinfo> proList =queryService.getTaskListPro(objectid, 1000);
//        while (flag) {
//            List<Detailedinfo> proList = queryService.getTaskListPro(objectid, 3000);
//            if (proList.size() == 0) {
//                break;
//            }
//            objectid = proList.get(proList.size() - 1).getObjectid();
//            for (Detailedinfo de : proList) {
//                doc = new SolrInputDocument();
//               // doc.addField("id", de.getObjectid());
//                doc.addField("id", de.getWebmpn());
//                doc.addField("objectid", de.getObjectid());
//                doc.addField("webmpn", de.getWebmpn());
//                doc.addField("url", de.getUrl());
//                doc.addField("mpn", de.getMpn());
//                docs.add(doc);
//                count++;
//            }
//            if(count%5000==0){
//                solrClient.add(docs);
//                solrClient.commit();
//                docs.clear();
//                System.out.println("已上传 " + count + " 条数据。");
//            }
//        }
//        if(docs.size()>0){
//            solrClient.add(docs);
//            solrClient.commit();
//            System.out.println("已上传 " + count + " 条数据。");
//        }
//    }
//
//    public int querySolr(String webmpn) throws Exception{
//        SolrQuery query = new SolrQuery();
//        String str="webmpn:IPD1-11-D-GP-M-ND";//+webmpn.replace("[","%5B").replace("]","%5D");
//        query.set("q",str);// 参数q  查询所有
//        QueryResponse response = solrClient.query(query);
//        SolrDocumentList solrDocumentList = response.getResults();
//       // System.out.println("查询结果的总数量：" + solrDocumentList.getNumFound());
//        System.out.println("查询结果的总数量：" + JSON.toJSONString(solrDocumentList));
//        return solrDocumentList.size();
//    }
//}
