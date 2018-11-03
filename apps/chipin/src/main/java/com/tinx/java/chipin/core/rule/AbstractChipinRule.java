package com.tinx.java.chipin.core.rule;

import com.tinx.java.chipin.core.sercet.SecretGuid;
import com.tinx.java.chipin.entity.ChipinLog;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.chipin.service.ChipinLogService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.service.UserLotteryService;
import com.tinx.java.chipin.utils.MapUtils;
import com.tinx.java.chipin.utils.RandomUtils;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tinx
 * @date 2018-10-8 21:43
 */
public abstract class AbstractChipinRule implements ChipinRule {


    private static Logger logger = LoggerFactory.getLogger(AbstractChipinRule.class);

    protected Task task;

    protected Lottery lottery;

    protected UserLottery userLottery;



    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public abstract TaskService getTaskService();

    public String rootUrl;

//    public CookieStore cookieStore;

    public String betsContent;

    public int betsSize;

    protected String periodNo;

    public void init(Task task,Lottery lottery,UserLottery userLottery){
        this.task = task;
        this.lottery = lottery;
        this.userLottery = userLottery;
    }
    /**
     * 获取当前期数
     */
    public String getCurrentPeriodStatus(){
        String currentPeriodStatusUrl = lottery.getCurrentPeriodStatusUrl();
        logger.info("Name=getCurrentPeriodStatus,Desc=获取当前期数,url={}",currentPeriodStatusUrl);
        String currPeriodNo = "";
        String url = String.format("%s%s%s", getRootUrl(),currentPeriodStatusUrl,System.currentTimeMillis());
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        CookieStore cookieStore = (CookieStore)MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            throw new RuntimeException("cookieStore is null!");
        }

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");
        httpGet.setHeader("Referer",getRootUrl()+"/App/Index?_=1530453466709");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Host",getRootUrl().replace("http://",""));
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
//            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("retStr value:"+retStr);
            currPeriodNo = JSONObject.fromObject(retStr).getJSONObject("Data").getString("real_period_no");
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.periodNo = currPeriodNo;
        return currPeriodNo;

    }

    /**
     * 上传下注文件，用来获取下注的参数格式
     * @return
     */
    public String uploadBetNos(){
        String uploadBetNosUrl = lottery.getUploadBetNosUrl();
        String chipinFilePath = task.getChipinFilePath();
        logger.info("Name=uploadBetNos,Desc=上传下注文件,url={}",uploadBetNosUrl);
        String rootUrl = getRootUrl();
        String url = String.format("%s%s",rootUrl,uploadBetNosUrl);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;

        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        File file = new File(chipinFilePath);
        CookieStore cookieStore = (CookieStore)MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            throw new RuntimeException("cookieStore is null!");
        }
        multipartEntityBuilder.addBinaryBody("file",file);
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        StringBuffer buffer = new StringBuffer();
        try{
            httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpPost.setHeader("Accept-Encoding","gzip, deflate");
            httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
            httpPost.setHeader("Referer",rootUrl+"/App/Index?_="+System.currentTimeMillis());
            httpPost.setHeader("Host",rootUrl.replace("http://",""));
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            int statusCode= httpResponse.getStatusLine().getStatusCode();
            System.out.println("statusCode:"+statusCode);
            if(statusCode == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
                String str = "";
                while(!StringUtils.isEmpty(str = reader.readLine())) {
                    buffer.append(str);
                }
                System.out.println("返回结果:"+buffer.toString());
            }

            closeableHttpClient.close();
            if(httpResponse!=null){
                httpResponse.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    protected String getBetsContent(Task task,Lottery lottery){
        if(StringUtils.isEmpty(betsContent)){
            String result = uploadBetNos();
            betsContent = generateBets(result,task.getMoney());
        }
        return betsContent;
    }

    protected String generateBets(String content, BigDecimal money){
        if(StringUtils.isEmpty(content)){
            return null;
        }
        String jsonContent = content.split("</script>")[1];
        System.out.println("jsonContent:"+jsonContent);
        JSONObject jsonObject = JSONObject.fromObject(jsonContent);
        String strJson = jsonObject.getJSONObject("Data").getString("Details");
        strJson = strJson.replace("\"bet_money\":\"\"","\"bet_money\":\""+money+"\"");
        System.out.println("generateBets JSON:"+strJson);
        betsSize = jsonObject.getJSONObject("Data").getJSONArray("Details").size();
        return strJson;
    }


    /**
     * 模拟下注
     * @return
     */
    public String simulateChipin(Task task, Lottery lottery){

        String result = "";
        String url = String.format("%s%s",getRootUrl(),lottery.getBatchBetUrl());
        logger.info("Name=simulateChipin,Desc=模拟下注,url={}",lottery.getBatchBetUrl());
        String periodNo = getCurrentPeriodStatus();
        String guid = SecretGuid.getGuid();
        String rootUrl = getRootUrl();
        List<NameValuePair> params = new ArrayList<>();
        getBetsContent(task,lottery);
        //params.add(new BasicNameValuePair("bets", "[{\"dict_no_type_id\":\"11\",\"bet_no\":\"1234\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"7\",\"bet_no\":\"123X\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"3\",\"bet_no\":\"4XX5\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"16\",\"bet_no\":\"5XXX5\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"15\",\"bet_no\":\"XXX12\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"18\",\"bet_no\":\"XX1X2\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"17\",\"bet_no\":\"X1XX2\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"2\",\"bet_no\":\"1X1X\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"1\",\"bet_no\":\"11XX\",\"bet_money\":\"1\"}]"));
        params.add(new BasicNameValuePair("bets",betsContent));
        params.add(new BasicNameValuePair("way", "103"));
        params.add(new BasicNameValuePair("guid", guid));
        params.add(new BasicNameValuePair("is_package", "0"));
        params.add(new BasicNameValuePair("period_no", periodNo));
        params.add(new BasicNameValuePair("TotalCount", ""+betsSize));
        params.add(new BasicNameValuePair("TotalBetMoney", "0"));
        params.add(new BasicNameValuePair("bet_money", task.getMoney().toString()));
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        CookieStore cookieStore = (CookieStore)MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            throw new RuntimeException("cookieStore is null!");
        }
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
            httpPost.setHeader("Accept-Encoding","gzip, deflate");
            httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.setHeader("Host",rootUrl.replace("http://",""));
            httpPost.setHeader("Origin",rootUrl);
            httpPost.setHeader("Proxy-Connection","keep-alive");
            httpPost.setHeader("Referer",rootUrl+"/App/Index?_="+System.currentTimeMillis());
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
            httpPost.setHeader("X-Requested-With","XMLHttpRequest");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);


//            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            logger.info("下注结果:"+retStr);
            int status = JSONObject.fromObject(retStr).getInt("Status");
            if (status != 0) {
                throw new RuntimeException("下注失败!");
            }
            if(status==1){
                result = "下注成功";
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            logger.error("下注失败!"+e.getMessage());
        }

        return result;

    }

    public String doJSONGet(String getUrl) {
        logger.info("Name=doJSONGet,getUrl={}",getUrl);
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        CookieStore cookieStore = (CookieStore)MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            throw new RuntimeException("cookieStore is null!");
        }
        HttpGet httpGet = new HttpGet(getUrl);
        httpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");
        httpGet.setHeader("Referer",getRootUrl()+"/Member/Agreement");
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
//            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("doJSONGet:retStr value:"+retStr);
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }

    /**
     * 获取账号余额
     *
     */
    public String getAccount(){
        String accountUrl = lottery.getAccountUrl();
        logger.info("Name=getAccount,Desc=获取账号余额,url={}",accountUrl);
        String url = String.format("%s%s%s",getRootUrl(),accountUrl,System.currentTimeMillis());
        String balance = "";
        String result = doJSONGet(url);
        logger.info("Name=getAccount,Desc=获取账号余额,返回结果,result={}",result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if(jsonObject.getInt("Status")==1){
            balance = jsonObject.getJSONObject("Data").getString("credit_balance");
        }
        if(jsonObject.getInt("Status")==5){
            balance = "err";
        }
        return balance;
    }


    public void stopTask(){
        Task task1 = new Task();
        task1.setId(task.getId());
        task1.setStatus(TaskStatusEnum.STOP.getCode());
        getTaskService().updateById(task1);
    }
}
