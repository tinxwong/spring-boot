package com.tinx.java.chipin.thread.entity;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.core.Authent;
import com.tinx.java.chipin.core.rule.ChipinRule;
import com.tinx.java.chipin.entity.*;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.chipin.service.ChipinLogService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.service.UserLotteryRuleService;
import com.tinx.java.chipin.service.UserLotteryService;
import com.tinx.java.chipin.utils.MapUtils;
import com.tinx.java.chipin.utils.RandomUtils;
import com.tinx.java.chipin.utils.TimestampUtils;
import com.tinx.java.common.utils.SpringContextHolder;
import com.tinx.java.common.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import org.apache.http.client.CookieStore;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author tinx
 * @date 2018-9-9 20:42
 */
public class ChipinTaskJob implements Callable{

    private static Logger logger = LoggerFactory.getLogger(ChipinTaskJob.class);

    private Cathectic cathectic;

    private Task task;

    private Lottery lottery;

    private UserLottery userLottery;


    public ChipinTaskJob(){

    }

    public ChipinTaskJob(Cathectic cathectic,Task task){
        this.cathectic = cathectic;
        this.task = task;
    }

    public ChipinTaskJob(Task task, Lottery lottery, UserLottery userLottery){
        this.task = task;
        this.lottery = lottery;
        this.userLottery = userLottery;
    }


    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    private boolean signal = true;

    @Override
    public Object call() throws Exception {
        String rootUrl = RandomUtils.getUrl(lottery.getRootUrl());
        Authent authent = null;
        try{
            authent = (Authent) SpringContextUtils.getBean(lottery.getAuthentClassName());
            authent.setRootUrl(rootUrl);
            authent.init(task,lottery,userLottery);
            authent.initCookieStore(false);
            authent.simulateAgreement();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        CookieStore cookieStore = (CookieStore) MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
        if(cookieStore==null){
            throw new RuntimeException("cookieStore is null!");
        }
        ChipinRule chipinRule = (ChipinRule) SpringContextUtils.getBean(task.getRuleName());
        chipinRule.init(task,lottery,userLottery);
        chipinRule.setRootUrl(rootUrl);
        while(isSignal()) {
            TaskService taskService = (TaskService)SpringContextUtils.getBean("taskServiceImpl");
            Task tempTask = taskService.selectById(task.getId());
//            authent.setTask(tempTask);
            ChipinLog chipinLog = chipinRule.execute(authent);
            if(tempTask.getStatus().intValue()==TaskStatusEnum.STOP.getCode()){
                setSignal(false);
            }else{
                try {
                    System.out.println("时间到了，开始执行！");
                    Map<String,String> map = TimestampUtils.getChipinTimeScope();
                    long chipipTime = TimestampUtils.getNextChipinTime(map);
                    long selectTime = chipipTime*1000-System.currentTimeMillis();
                    String nextTime = TimestampUtils.timeStamp2Date(String.valueOf(chipipTime),"");
                    logger.info("当前执行时间={},下次执行范围={},睡眠时间长度={},下次执行时间={}", TimestampUtils.currDate(""),map,selectTime, nextTime);
                    chipinLog.setChipinTimeScope(map.toString());
                    chipinLog.setNextChipinTime(nextTime);
                    ChipinLogService chipinLogService = (ChipinLogService)SpringContextUtils.getBean("chipinLogServiceImpl");
                    try{
                        chipinLogService.insert(chipinLog);
                    }catch (Exception e){
                        logger.error(e.getMessage());
                    }

                    Thread.sleep(selectTime);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

//        while(isSignal()){
//            if(cathectic.getCookieStore()==null){
//                cathectic.simulateLogin();
//                Thread.sleep(1000);
//                cathectic.simulateAgreement();
//                Thread.sleep(1000);
//            }
//            BigDecimal  balance = cathectic.getAccountBalance();
//            if(balance.compareTo(task.getAccountMaxLimit())==1){
//                setSignal(false);
//                continue;
//            }
//            if(balance.compareTo(task.getAccountMinLimit())==-1){
//                setSignal(false);
//                continue;
//            }
//            cathectic.simulateChipin();
//            logger.info("");
//            Thread.sleep(task.getIntervalTime()*60*1000);
//            System.out.println("线程启动，开始投注工作！");
//            Thread.sleep(5000);
//        }
        return null;
    }
}
