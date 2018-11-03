package com.tinx.java.chipin.core.rule;

import com.tinx.java.chipin.core.Authent;
import com.tinx.java.chipin.entity.ChipinLog;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.service.ChipinLogService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.service.UserLotteryService;
import com.tinx.java.chipin.utils.FileUtils;
import com.tinx.java.common.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tinx
 * @date 2018-10-4 23:07
 */
@Component
public class PeriodNumberChipinRule extends AbstractChipinRule {

    @Autowired
    private UserLotteryService userLotteryService;

    @Autowired
    private ChipinLogService chipinLogService;

    private static Logger logger = LoggerFactory.getLogger(PeriodNumberChipinRule.class);

    @Autowired
    private TaskService taskService;

    public TaskService getTaskService(){
        return taskService;
    }
    @Override
    public int getRuleId() {
        return 3;
    }

    @Override
    public String getRuleName() {
        return "periodNumberChipinRule";
    }

    public String getRuleDesc(){
        return "往期号码规则";
    }

    public UserLotteryService getUserLotteryService(){
        return userLotteryService;
    }

    @Override
    public ChipinLog execute(Authent authent) {
        logger.info("Name=PeriodNumberChipinRule,Desc={},time={}",getRuleDesc(), DateUtil.format(new Date(),DateUtil.TIMESTAMP_PATTERN));
        BigDecimal accountMax = task.getAccountMaxLimit();
        BigDecimal accountMin = task.getAccountMinLimit();
        String balance = getAccount();
        String result = "";
        BigDecimal accountBalance = new BigDecimal(balance);
        logger.info("accountMax={},accountMin={},balance={}",accountMax,accountMin,balance);
        if(accountBalance.compareTo(accountMin)==-1||accountBalance.compareTo(accountMax)==1){
            result = String.format("MAX:%s,MIN:%s,BALANCE:%s",accountMax.toString(),accountMin.toString(),balance);
        }else{
            if(!matchDrawNoTable(task,lottery)){
//                result = simulateChipin(task,lottery);
                result="执行下注功能";
            }else{
                result = "下注号码匹配了开奖号码，取消执行下注功能";
            }
            try {
                logger.info("睡眠["+task.getIntervalTime()+"]分钟");
                Thread.sleep(task.getIntervalTime()*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ChipinLog();
    }


    /**
     * 匹配往期开奖号码，如果匹配成功，返回true,否则返回false
     * @param task
     * @param lottery
     * @return
     */
    public boolean matchDrawNoTable(Task task, Lottery lottery){
        logger.info("Name=matchMemberPrints,Desc=匹配往期开奖号码,url={}",lottery.getBatchBetUrl());
        List<String> drawNoTables = getDrawNoTable(lottery.getDrawNoTableUrl(),task.getPeriodNum());
        List<String> chipins = getChipinContent(task.getChipinFilePath());
        boolean isTrue = false;
        for(String str:drawNoTables){
            for(String ci:chipins){
                for(int i = 0;i < ci.length(); i++){
                    if (!Character.isDigit(ci.charAt(i))){
                        continue;
                    }
                    if(ci.charAt(i)!=str.charAt(i)){
                        isTrue = false;
                        break;
                    }
                    isTrue = true;
                }
                if(isTrue){
                    logger.info("匹配成功,下注码:"+ci+";历史码:"+str);
                    break;
                }

            }
            if(isTrue){
                break;
            }else{
                logger.info("匹配不成功，开始下注!");
            }
        }
        return isTrue;
    }

    /**
     * 获取往期开奖号码
     * @param draqNoTableUrl
     * @param periodNum
     * @return
     */
    public List<String> getDrawNoTable(String draqNoTableUrl,int periodNum){
        logger.info("Name=getDrawNoTable,Desc=获取往期开奖号码,url={},periodNum={}",draqNoTableUrl,periodNum);
        String url = String.format("%s%s%s",getRootUrl(),draqNoTableUrl,System.currentTimeMillis());
        String result = doJSONGet(url);
        JSONObject jsonObject = JSONObject.fromObject(result);
        List<String> list = new ArrayList<>();
        if(jsonObject.getInt("Status")==1){
            JSONArray jsonArray = jsonObject.getJSONObject("Data").getJSONArray("Rows");
            int size = jsonArray.size();
            if(periodNum>0&&periodNum<size){
                size = periodNum;
            }
            for(int i = 0;i < size; i++){
                JSONObject json = (JSONObject)jsonArray.get(i);
                String number = String.format("%s%s%s%s%s",json.getString("thousand_no"),json.getString("hundred_no"),json.getString("ten_no"),json.getString("one_no"),json.getString("ball5"));
                logger.info("期数={}, 开奖号码={};",json.getString("period_no"),number);

                list.add(number);
            }
        }
        return list;
    }


    public List<String> getChipinContent(String chipinFilePath){
        return FileUtils.fileContentToList(chipinFilePath);
    }





}
