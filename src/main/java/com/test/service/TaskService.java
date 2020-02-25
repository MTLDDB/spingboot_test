package com.test.service;

import com.test.mapper.TaskMapper;
import com.test.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;
   public List<Task> getTaskList(int start, int pageSize){
       return taskMapper.getTaskList(start,pageSize);
   }
}
