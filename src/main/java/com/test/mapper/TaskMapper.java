package com.test.mapper;

import com.test.model.Detailedinfo;
import com.test.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface TaskMapper {
    @Select("select * from tt_mytask where status=0 order by objectid limit #{start},#{pageSize};")
    List<Task> getTaskList(@Param("start") int start, @Param("pageSize") int pageSize);

    @Select("select * from tt_mytask where status=0 and objectid>#{objectid} order by objectid asc limit #{pageSize};")
    List<Task> getTaskList2(@Param("objectid") String objectid, @Param("pageSize") int pageSize);
    @Select("select objectid from tt_detailedinfo where webmpn=#{webmpn};")
    String getIdbyWebmpn(@Param("webmpn") String webmpn);

    @Select("select * from tt_detailedinfo where  objectid>#{objectid} order by objectid asc limit #{pageSize};")
    List<Detailedinfo> getTaskListPro(@Param("objectid") String objectid, @Param("pageSize") int pageSize);

    @Select("select objectid from tt_detailedinfo  limit 0,1;")
    String getTaskListProOne();

    @Update("update tt_mytask set status=2 where objectid=#{objectid};")
    void updateTaskStatues(@Param("objectid") String objectid);

    @Select("select * from tt_mytask where status=0 limit #{pageSize};")
    List<Task> getTaskList1(@Param("pageSize")int pageSize);
}
