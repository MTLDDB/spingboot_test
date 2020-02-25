package com.test.model;

import java.util.List;
import java.util.Map;

public class FutureResult {
    private List<Task> taskList;
    private boolean done;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
