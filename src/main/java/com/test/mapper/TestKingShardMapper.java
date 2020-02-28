package com.test.mapper;

import com.test.model.Detailedinfo;
import com.test.model.Price;
import com.test.model.Stock;
import com.test.model.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TestKingShardMapper {
    @Insert("insert into tt_stock (objectid,stock,detail_id) values(#{id},#{stock},#{detail_id})")
    void insert(@Param("id") String id,@Param("stock") String stock,@Param("detail_id") String detail_id);

    @Select("select * from tt_stock where ${name}=#{value} order by create_time acs limit 3000")
    List<Stock> get(@Param("name") String name,@Param("value") String value);

    @Delete("delete from tt_detailedinfo where objectid=#{id}")
    void delete(@Param("id") String id);
    @Select("select * from tt_mytask where ${name}=#{value} ;")
    List<Task> getUrl(@Param("name") String name, @Param("value") String value);

    @Delete("delete from tt_additional_resources")
    void deleteResources();
    @Delete("delete from tt_alternate_package")
    void deleteAlternate_package();
    @Delete("delete from tt_attributes")
    void deleteAttributes();
    @Delete("delete from tt_detailedinfo")
    void deleteDetailedinfo();
    @Delete("delete from tt_documents_media")
    void deleteDocuments_media();
    @Delete("delete from tt_environmental")
    void deleteEnvironmental();
    @Delete("delete from tt_interest")
    void deleteInterest();
    @Delete("delete from tt_myprioritytask")
    void deleteMyprioritytask();
    @Delete("delete from tt_mytask")
    void deleteMytask();
    @Delete("delete from tt_price")
    void deletePrice();
    @Delete("delete from tt_stock")
    void deleteStock();
    @Delete("delete from tt_stock_now")
    void deleteStock_now();
    @Delete("delete from tt_substitute")
    void deleteSubstitute();
    @Delete("delete from tt_urllist")
    void deleteUrlList();

    @Select("select * from tt_detailedinfo where objectid=#{s}")
    List<Detailedinfo> getProduct(@Param("s") String s);

    @Select("select * from tt_detailedinfo where ${clo}=#{s}")
    List<Detailedinfo> getProductBy(@Param("clo") String clo,@Param("s") String s);

    @Select("select price_json from tt_price where detail_id=#{id};")
    String getPrice(@Param("id") String  id);
    //https://www.verical.com/pd/iei-technology-box-pcs-tank-870ai-i7-8g-2a-r11-6254309
    @Select("select url from tt_mytask where status=0 order by createtime asc ;")
    List<Task> getTask();
    @Select("select * from ${table} ;")
    List<Task> getTask1(@Param("table") String table);
    @Select("select attributes_json from tt_attributes where objectid=#{id}")
    String getAttr(@Param("id") String id);

    @Select("select * from tt_detailedinfo where mfr ='Renesas Electronics America';")
    List<Detailedinfo> getMfr();

    @Update("update  tt_mytask set status=0 where objectid=#{id}")
    void update(@Param("id") String objectid);

    @Select("select ifnull('notnull', 'nulltest') from tt_detailedinfo where webmpn=#{webmpn};")
    String getIdbyWebmpn(@Param("webmpn") String webmpn);
}
