package com.tinx.java.chipin.core.rule;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.core.Authent;
import com.tinx.java.chipin.entity.ChipinLog;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Sysconfig;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.service.ChipinLogService;
import com.tinx.java.chipin.service.SysconfigService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author tinx
 * @date 2018-10-4 23:08
 */
@Component
public class AccountChangeChipinRule extends AbstractChipinRule {
    private static Logger logger = LoggerFactory.getLogger(AccountChangeChipinRule.class);

    @Autowired
    private ChipinLogService chipinLogService;

    @Autowired
    private TaskService taskService;

    public TaskService getTaskService(){
        return taskService;
    }

    public ChipinLogService getChipinLogService(){
        return chipinLogService;
    }

    @Override
    public int getRuleId() {
        return 2;
    }

    @Override
    public String getRuleName() {
        return "accountChangeChipinRule";
    }

    public String getRuleDesc(){
        return "账户变动规则";
    }

    public ChipinLog execute(Authent authent) {
        String  account_Key = String.format("%s_%s_%s_%s_%s_ACCOUNT",task.getId(),userLottery.getUserId(),lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd());
        String counter_key = String.format("%s_%s_%s_%s_%s_COUNTER",task.getId(),userLottery.getUserId(),lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd());
        String result = "";
        String currAccount = "";
        BigDecimal accountMax = task.getAccountMaxLimit();
        BigDecimal accountMin = task.getAccountMinLimit();
        String account = (String)MapUtils.get(account_Key);
        if(StringUtils.isEmpty(account)){
            logger.info("Name=AccountChangeChipinRule,Desc=账户变动规则,开始执行,获取账号，余额={}"+account);
            account = getAccount();
            MapUtils.put(account_Key,account);
            simulateChipin(task,lottery);
        }else{
            currAccount = getAccount();
            BigDecimal currAccountBG = new BigDecimal(currAccount);
            BigDecimal accountBG = new BigDecimal(account);
            logger.info("Name=AccountChangeChipinRule,Desc=账户变动规则,上一期账号余额={}，本期账号余额={}",account,currAccount);
            if(currAccountBG.compareTo(accountMin)==-1||currAccountBG.compareTo(accountMax)==1){
                result = String.format("MAX:%s,MIN:%s,BALANCE:%s",accountMax.toString(),accountMin.toString(),currAccount);
                logger.info("Name=AccountChangeChipinRule,Desc=账户变动规则,不执行:"+result);
            }else{
                if(currAccountBG.compareTo(accountBG)==1){
                    logger.info("Name=AccountChangeChipinRule,Desc=账户变动规则,本期账号余额大于上期账号余额,执行下注:上一期账号余额={}，本期账号余额={}",account,currAccount);
                    simulateChipin(task,lottery);
                    MapUtils.put(account_Key,currAccount);
                }else{
                    BigDecimal intervalPeriods = task.getIntervalPeriods();
                    BigDecimal counter = (BigDecimal)MapUtils.get(counter_key);
                    if(counter==null){
                        counter = BigDecimal.ONE;
                    }
                    logger.info("Name=AccountChangeChipinRule,Desc=账户变动规则,本期账号余额小于上期账号余额,暂停下注:上一期账号余额={}，本期账号余额={},计数器={},设置的值={}",account,currAccount,counter,intervalPeriods);

                    if(counter.compareTo(intervalPeriods)==1){
                        logger.info("计数器大于设置的值,执行下注;计数器={},设置的值={}，执行下注:",counter,intervalPeriods);
                        simulateChipin(task,lottery);
                        MapUtils.remove(counter_key);
                    }else{

                        counter.add(BigDecimal.ONE);
                        MapUtils.put(counter_key,counter);
                        logger.info("计数器小于设置的值，计数器自增;计数器={},设置的值={}:",counter,intervalPeriods);
                    }
                }
            }

        }
        try {
            logger.info("睡眠["+task.getIntervalTime()+"]分钟");
            Thread.sleep(task.getIntervalTime()*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ChipinLog();
    }

    @Override
    public String getRootUrl() {
        return null;
    }
}
