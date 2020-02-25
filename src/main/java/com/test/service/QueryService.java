package com.test.service;

import com.test.mapper.TaskMapper;
import com.test.model.Detailedinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    @Autowired
    private TaskMapper taskMapper;
    public List<Detailedinfo> getTaskListPro(String objectid, int pageSize){
        return taskMapper.getTaskListPro(objectid,pageSize);
    }

    public String getTaskListProOne() {
        return taskMapper.getTaskListProOne();
    }
}
