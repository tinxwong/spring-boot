package com.tinx.java.chipin.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;
/**
 * @author tinx
 * @date 2018-9-9 22:13
 */

public interface IThreadPoolMonitorService extends Runnable {

    public void monitorThreadPool();
}
