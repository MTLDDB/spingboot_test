//package com.test.updater;
//
//import com.test.mapper.TaskMapper;
//import com.test.model.Task;
//import com.test.solr.UploadProduct;
//import com.test.tool.ScheduleSend;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Updater_solr implements Runnable{
//    Logger logger = LoggerFactory.getLogger(Updater_solr.class);
//    private List<Task> taskList;
//    private TaskMapper taskMapper;
//    private UploadProduct uploadProduct;
//    public Updater_solr(List<Task> taskList,TaskMapper taskMapper,UploadProduct uploadProduct) {
//        this.taskList = taskList;
//        this.taskMapper=taskMapper;
//        this.uploadProduct=uploadProduct;
//    }
//    @Override
//    public void run() {
//        try {
//            for (Task task : taskList) {
//                String url = task.getUrl();
//                if (url.contains("/products/")) {
//                    continue;
//                }
//                List<String> stringList = Arrays.asList(url.toString().trim().split("/"));
//                int len = stringList.size();
//                String webmpn = stringList.get(len - 2);
//
//                if (uploadProduct.querySolr(webmpn) == 0) {
//                    continue;
//                } else {
//                    logger.info("已存在！");
//                    logger.info("++++" + webmpn);
//                    taskMapper.updateTaskStatues(task.getObjectid());
//                    ScheduleSend.updateNo.addAndGet(1);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("任务状态更新出错！错误原因：" + e);
//        }
//    }
//}
