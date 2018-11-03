package com.tinx.java.chipin.thread;

import com.tinx.java.chipin.service.ThreadPoolMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author tinx
 * @date 2018-9-9 22:16
 */
@Component
public class Starter implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(Starter.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("监控线程池任务启动！");
        Thread monitor = new Thread(new ThreadPoolMonitorService());
        monitor.start();
    }
}
