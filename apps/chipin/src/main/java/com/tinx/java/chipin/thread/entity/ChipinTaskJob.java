package com.tinx.java.chipin.thread.entity;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.common.Constraint;
import com.tinx.java.chipin.core.Authent;
import com.tinx.java.chipin.core.rule.ChipinRule;
import com.tinx.java.chipin.entity.*;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.chipin.service.*;
import com.tinx.java.chipin.utils.MapUtils;
import com.tinx.java.chipin.utils.RandomUtils;
import com.tinx.java.chipin.utils.TimestampUtils;
import com.tinx.java.common.response.enums.VisibilityEnum;
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


    private Task task;

//    private Lottery lottery;
//
//    private UserLottery userLottery;


    public ChipinTaskJob(){

    }

//    public ChipinTaskJob(Cathectic cathectic,Task task){
//        this.cathectic = cathectic;
//        this.task = task;
//    }

    public ChipinTaskJob(Task task){
        this.task = task;
    }
//    public ChipinTaskJob(Task task, Lottery lottery, UserLottery userLottery){
//        this.task = task;
//        this.lottery = lottery;
//        this.userLottery = userLottery;
//    }


    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    private boolean signal = true;

    @Override
    public Object call() throws Exception {

        LotteryService lotteryService = (LotteryService)SpringContextUtils.getBean("lotteryServiceImpl");
        UserLotteryService userLotteryService = (UserLotteryService)SpringContextUtils.getBean("userLotteryServiceImpl");
        TaskService taskService = (TaskService)SpringContextUtils.getBean("taskServiceImpl");
        UserLottery userLottery = userLotteryService.selectByParam(task.getUserId(),task.getLotteryId());
        Lottery lottery = lotteryService.selectOne(Condition.create().eq("id",task.getLotteryId()).eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        if(lottery==null){
            throw new RuntimeException("启动任务["+task.getId()+"]失败，不存在该下注网站["+task.getLotteryId()+"]");
        }
        String rootUrl = RandomUtils.getUrl(lottery.getRootUrl());
        try{
            Authent authent = (Authent) SpringContextUtils.getBean(lottery.getAuthentClassName());
            CookieStore cookieStore = authent.simulateLogin(rootUrl,lottery.getLoginUrl(),userLottery.getLoginUser(),userLottery.getLoginPwd());
            ChipinRule chipinRule = null;
            try{
                String ruleName = task.getRuleName();
                Class clazz = Class.forName(String.format("%s.%s", Constraint.RULE_PACKAGE,ruleName));
                chipinRule = (ChipinRule)clazz.newInstance();
                chipinRule.init(task,lottery,userLottery);
                chipinRule.setCookieStore(cookieStore);
                chipinRule.setRootUrl(rootUrl);
                chipinRule.simulateAgreement();
            }catch (Exception e){
                e.printStackTrace();
            }
            if(chipinRule==null){
                logger.error("chipinRule is null!");
                return null;
            }
            ChipinLog chipinLog = new ChipinLog();
            while(isSignal()) {
                chipinLog.setId(null);
                chipinLog = chipinRule.execute(authent);
                Task tempTask = taskService.selectById(task.getId());
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
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
        return null;
    }


    public String getRondomRootUrl(Long lotteryId){
        LotteryService lotteryService = (LotteryService)SpringContextUtils.getBean("lotteryServiceImpl");
        Lottery lottery = lotteryService.selectOne(Condition.create().eq("id",lotteryId).eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        return RandomUtils.getUrl(lottery.getRootUrl());
    }


//    public Object call() throws Exception {
//        String rootUrl = RandomUtils.getUrl(lottery.getRootUrl());
//        Authent authent = null;
//        try{
//            authent = (Authent) SpringContextUtils.getBean(lottery.getAuthentClassName());
//            authent.setRootUrl(rootUrl);
//            authent.init(task,lottery,userLottery);
//            authent.initCookieStore(false);
//            authent.simulateAgreement();
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return null;
//        }
//
//        CookieStore cookieStore = (CookieStore) MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
//        if(cookieStore==null){
//            throw new RuntimeException("cookieStore is null!");
//        }
//        ChipinRule chipinRule = (ChipinRule) SpringContextUtils.getBean(task.getRuleName());
//        chipinRule.init(task,lottery,userLottery);
//        chipinRule.setRootUrl(rootUrl);
//        while(isSignal()) {
//            TaskService taskService = (TaskService)SpringContextUtils.getBean("taskServiceImpl");
//            Task tempTask = taskService.selectById(task.getId());
//            ChipinLog chipinLog = chipinRule.execute(authent);
//            if(tempTask.getStatus().intValue()==TaskStatusEnum.STOP.getCode()){
//                setSignal(false);
//            }else{
//                try {
//                    System.out.println("时间到了，开始执行！");
//                    Map<String,String> map = TimestampUtils.getChipinTimeScope();
//                    long chipipTime = TimestampUtils.getNextChipinTime(map);
//                    long selectTime = chipipTime*1000-System.currentTimeMillis();
//                    String nextTime = TimestampUtils.timeStamp2Date(String.valueOf(chipipTime),"");
//                    logger.info("当前执行时间={},下次执行范围={},睡眠时间长度={},下次执行时间={}", TimestampUtils.currDate(""),map,selectTime, nextTime);
//                    chipinLog.setChipinTimeScope(map.toString());
//                    chipinLog.setNextChipinTime(nextTime);
//                    ChipinLogService chipinLogService = (ChipinLogService)SpringContextUtils.getBean("chipinLogServiceImpl");
//                    try{
//                        chipinLogService.insert(chipinLog);
//                    }catch (Exception e){
//                        logger.error(e.getMessage());
//                    }
//
//                    Thread.sleep(selectTime);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return null;
//    }
}
