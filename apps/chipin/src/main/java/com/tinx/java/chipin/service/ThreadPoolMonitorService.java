package com.tinx.java.chipin.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author tinx
 * @date 2018-9-9 22:06
 */
@Component
public class ThreadPoolMonitorService implements IThreadPoolMonitorService {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolMonitorService.class);

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    private long monitoringPeriod;
    public void run() {
        logger.info("ThreadPoolMonitorService start!");
        try {
            while (true){
                monitorThreadPool();
                Thread.sleep(monitoringPeriod*1000);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void monitorThreadPool() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("CurrentPoolSize : ").append(taskExecutor.getPoolSize());
        strBuff.append(" - CorePoolSize : ").append(taskExecutor.getCorePoolSize());
        strBuff.append(" - MaximumPoolSize : ").append(taskExecutor.getMaxPoolSize());
        strBuff.append(" - ActiveTaskCount : ").append(taskExecutor.getActiveCount());
//        strBuff.append(" - CompletedTaskCount : ").append(executor.getCompletedTaskCount());
//        strBuff.append(" - TotalTaskCount : ").append(executor.getTaskCount());
        strBuff.append(" - isTerminated : ").append(taskExecutor.isDaemon());
        logger.debug(strBuff.toString());
    }


    public long getMonitoringPeriod() {
        return monitoringPeriod;
    }

    public void setMonitoringPeriod(long monitoringPeriod) {
        this.monitoringPeriod = monitoringPeriod;
    }
}
