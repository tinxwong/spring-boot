package com.tinx.java.junit;

import com.tinx.java.chipin.utils.TimestampUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author tinx
 * @date 2018-10-26 10:26
 */
public class DemoTaskJob implements Callable {

    private static Logger logger = LoggerFactory.getLogger(com.tinx.java.chipin.thread.entity.DemoTaskJob.class);

    @Override
    public Object call() throws Exception {
        while(true){
            System.out.println("时间到了，开始执行！");
            Map<String,String> map = TimestampUtils.getChipinTimeScope();
            long chipipTime = TimestampUtils.getNextChipinTime(map);
            long selectTime = chipipTime*1000-System.currentTimeMillis();
            logger.info("当前执行时间={},下次执行范围={},睡眠时间长度={},下次执行时间={}", TimestampUtils.currDate(""),map,selectTime, TimestampUtils.timeStamp2Date(String.valueOf(chipipTime),""));
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
            Thread.sleep(selectTime);
        }
    }
}
