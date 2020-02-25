package com.test.service;

import com.test.mapper.TestKingShardMapper;
import com.test.model.Detailedinfo;
import com.test.model.Price;
import com.test.model.Stock;
import com.test.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestKingShardMapper testKingShardMapper;
    public void insert( String id, String stock,String s){
        testKingShardMapper.insert(id,stock,s);
    }
public List<Stock> get(){
       return testKingShardMapper.get("objectid","59b8c3ed2e064cb3982c24af63619ef5");
}
    public List<Task> getUrl(String name, String value){
        return testKingShardMapper.getUrl(name,value);
    }
public void delete(String id){
        testKingShardMapper.delete(id);
}
    public void deleteAll(){
//        testKingShardMapper.deleteStock();
//        testKingShardMapper.deleteEnvironmental();
//        testKingShardMapper.deleteAlternate_package();
//        testKingShardMapper.deleteAttributes();
//        testKingShardMapper.deleteDetailedinfo();
//        testKingShardMapper.deleteDocuments_media();
//        testKingShardMapper.deleteUrlList();
//        testKingShardMapper.deleteSubstitute();
//        testKingShardMapper.deleteResources();
//        testKingShardMapper.deleteStock_now();
//        testKingShardMapper.deletePrice();
//        /////////testKingShardMapper.deleteMytask();
        testKingShardMapper.deleteMyprioritytask();
//        testKingShardMapper.deleteInterest();
    }

    public List<Detailedinfo> getProduct(String s) {
        return testKingShardMapper.getProduct(s);
    }
    public List<Detailedinfo> getProductBy(String clo,String s) {
        return testKingShardMapper.getProductBy(clo,s);
    }
    public List<Task> getTask() {
        return testKingShardMapper.getTask();
    }
    public List<Task> getTask1(String table) {
        return testKingShardMapper.getTask1(table);
    }
    public String getAttr(String id) {
        return testKingShardMapper.getAttr(id);
    }

    public List<Detailedinfo> getMfr() {
        return testKingShardMapper.getMfr();
    }
    public List<Price> getPrice(String id){
        return  testKingShardMapper.getPrice(id);
    }
    public void update(String objectid) {
        testKingShardMapper.update(objectid);
    }
    public String getIdbyWebmpn(String webmpn){
     return testKingShardMapper.getIdbyWebmpn(webmpn);
    }
}
