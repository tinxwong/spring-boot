package com.tinx.java.junit;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tinx
 * @date 2018-10-19 10:28
 */
public class MyTest {



    public static List<String> chipinList = new ArrayList<>();

    public static List<String> allList = new ArrayList<>();

    static{
        initAll();
        initChipin();

    }


    public static void initAll(){
        allList.add("18769");
        allList.add("43782");
        allList.add("89760");
        allList.add("24783");
    }

    public static void initChipin(){
        chipinList.add("1876");
        chipinList.add("14634");
        chipinList.add("24783");
        chipinList.add("1xx6");
        chipinList.add("x2xx5");
        chipinList.add("2xx8");
        chipinList.add("x4xx3");
        chipinList.add("4xx3");
    }

    public static void main(String[] args){
        ThreadPoolTaskExecutor poolTaskExecutor = initThreadPoolTaskExecutor();
        try{
            DemoTaskJob demoTaskJob = new DemoTaskJob();
//            FutureTask task = new FutureTask(demoTaskJob);
            poolTaskExecutor.submit(demoTaskJob);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static ThreadPoolTaskExecutor initThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setQueueCapacity(10000);
        poolTaskExecutor.setCorePoolSize(5);
        poolTaskExecutor.setMaxPoolSize(10);
        poolTaskExecutor.setKeepAliveSeconds(5000);
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }
}
