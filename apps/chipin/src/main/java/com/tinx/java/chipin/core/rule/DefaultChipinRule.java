package com.tinx.java.chipin.core.rule;

import com.tinx.java.chipin.core.Authent;
import com.tinx.java.chipin.core.sercet.SecretGuid;
import com.tinx.java.chipin.entity.ChipinLog;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.service.ChipinLogService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.utils.MapUtils;
import com.tinx.java.chipin.utils.RandomUtils;
import com.tinx.java.common.utils.DateUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tinx
 * @date 2018-10-7 17:48
 */
@Component
public class DefaultChipinRule extends AbstractChipinRule {

    private static Logger logger = LoggerFactory.getLogger(DefaultChipinRule.class);

    private String rootUrl;
    @Autowired
    private ChipinLogService chipinLogService;

    public ChipinLogService getChipinLogService(){
        return chipinLogService;
    }

    @Autowired
    private TaskService taskService;

    public TaskService getTaskService(){
        return taskService;
    }
    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public DefaultChipinRule(){}

    public DefaultChipinRule(Lottery lottery,Task task){
        this.lottery = lottery;
        this.task = task;
        this.rootUrl = RandomUtils.getUrl(lottery.getRootUrl());
    }


    public ChipinLog execute(Authent authent){
        logger.info("Name=DefaultChipinRule,Desc={},time={}",getRuleDesc(), DateUtil.format(new Date(),DateUtil.TIMESTAMP_PATTERN));
        BigDecimal accountMax = task.getAccountMaxLimit();
        BigDecimal accountMin = task.getAccountMinLimit();
        String balance = getAccount();
        if(balance=="err"){
            logger.info("获取账号失败，执行重新登录操作！balance={}",balance);
            authent.initCookieStore(true);
            balance = getAccount();
        }
        String result = "";
        BigDecimal accountBalance = new BigDecimal(balance);
        logger.info("Name=DefaultChipinRule,Desc={},accountMax={},accountMin={},balance={}",getRuleDesc(),accountMax,accountMin,balance);
        if(accountBalance.compareTo(accountMin)==-1||accountBalance.compareTo(accountMax)==1){
            logger.info("Name=execute,Desc={},修改任务[taskid={}]状态,停止任务}",getRuleDesc(),task.getId());
            result = String.format("停止任务,accountMax=%s,accountMin=%s,balance=%s",accountMax,accountMin,balance);
            stopTask();
        }else{
            result = simulateChipin(task,lottery);

        }
        ChipinLog chipinLog = new ChipinLog();
        chipinLog.setBetsContent(betsContent);
        chipinLog.setBetsSize(betsSize);
        chipinLog.setTaskId(task.getId());
        chipinLog.setUserName(task.getUserName());
        chipinLog.setUserId(task.getUserId());
        chipinLog.setBetMoney(task.getMoney().toString());
        chipinLog.setPeriodNo(periodNo);
        chipinLog.setResults(result);
        chipinLog.setAccountBalance(accountBalance.toString());
        return chipinLog;
    }

    public int getRuleId(){
        return 1;
    }

    public String getRuleName(){
        return "defaultChipinRule";
    }

    @Override
    public String getRuleDesc() {
        return "默认规则";
    }

    public Lottery getLottery(){
        return lottery;
    }

    public Task getTask(){
        return task;
    }


}
