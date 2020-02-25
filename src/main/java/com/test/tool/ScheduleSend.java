//package com.test.tool;
//
//import com.test.mapper.TaskMapper;
//import com.test.model.FutureResult;
//import com.test.model.Task;
//import com.test.pool.ThreadPool;
//import com.test.solr.UploadProduct;
//import com.test.updater.Updater_solr;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//
//@Component
//@EnableScheduling
//public class ScheduleSend implements SchedulingConfigurer {
//    public static AtomicInteger updateNo=new AtomicInteger(0);
//    private static final Logger logger = LoggerFactory.getLogger(ScheduleSend.class);
//    @Autowired
//    private TaskMapper taskMapper;
//    @Autowired
//    private UploadProduct uploadProduct;
//    private int interval = 5;//间隔时间
//    private final static AtomicInteger counter = new AtomicInteger(0);
//    private static boolean sleep=false;
//    private int no=0;
//    private int start=10;
//    private String objectid = "0";
//    private int startLocal=0;
//    /**
//     * 执行定时任务.
//     */
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(
//                // 1.添加任务内容(Runnable)
//                () -> {
//                    int queueSize=ThreadPool.getThreadPoolExecutor().getQueue().size();
//                    int runThreadNo=ThreadPool.getThreadPoolExecutor().getActiveCount();
//
//                    int pageSize=3000;
//                    if(queueSize==0&&runThreadNo==0){
//                        no=no+updateNo.get();
//                        logger.info("updateNO : "+no+"!");
//                        sleep=false;
//                    }else{
//                        sleep=true;
//                        logger.info("未完成！继续sleep.");
//                    }
//                    if(!sleep){
//                        updateNo.set(0);
//                        counter.addAndGet(1);
//                        logger.info("gettask第"+counter.get()+"次");
//                        start=startLocal+pageSize*(counter.get()-1)-no;
//                        logger.info("start : "+start);
//                       // List<Task> taskList = taskMapper.getTaskList2(objectid,pageSize);
//                        List<Task> taskList = taskMapper.getTaskList(start,pageSize);
//                       // List<Task> taskList = taskMapper.getTaskList1(pageSize);
//                        if(taskList.size()==0)System.exit(0);
//                        objectid=taskList.get(taskList.size()-1).getObjectid();
//                        List<Task> newList=new ArrayList<>();
//                        int len=taskList.size();
//                        if ( len== 0) System.exit(0);
//                        int count=len/20;
//                        int RunSize=20;//(taskList.size()/count)+1;
//                        for (int i = 0; i < RunSize; i++) {
//                            if ((i + 1) == RunSize) {
//                                int startIndex = (i * count);
//                                int endIndex = taskList.size();
//                                newList = taskList.subList(startIndex, endIndex);
//                            } else {
//                                int startIndex = i * count;
//                                int endIndex = (i + 1) * count - 1;
//                                newList = taskList.subList(startIndex, endIndex);
//                            }
//                            //ThreadPool.getThreadPoolExecutor().submit(new Updater(newList,taskMapper));
//                            ThreadPool.getThreadPoolExecutor().submit(new Updater_solr(newList,taskMapper,uploadProduct));
//                        }
//                    }
//                },
//                // 2.设置执行周期(Trigger)
//                triggerContext -> {
//                    // 任务触发，可修改任务的执行周期
//                    CronTrigger trigger = new CronTrigger("0/" + interval + " * * * * ?");//每隔interval触发一次
//                    Date nextExecutionTime = trigger.nextExecutionTime(triggerContext);
//                    //testStatistical.show();
//                    return nextExecutionTime;
//                });
//
//    }
//}
