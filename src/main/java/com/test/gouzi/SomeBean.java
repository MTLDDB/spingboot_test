package com.test.gouzi;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class SomeBean implements InitializingBean, DisposableBean {

    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");// 初始化
    }

    public void destroy() throws Exception {
        System.out.println("释放资源");// 释放资源
    }
}