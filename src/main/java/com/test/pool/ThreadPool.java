package com.test.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static ThreadPoolExecutor threadPoolExecutor;
    static BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>(100);
    public static ThreadPoolExecutor init(){
       return threadPoolExecutor=new ThreadPoolExecutor(20,25,5, TimeUnit.SECONDS,queue);
    }
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }
}
