package com.tinx.java.chipin.core;

import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.utils.MapUtils;
import com.tinx.java.chipin.utils.RandomUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tinx
 * @date 2018-10-7 18:39
 */
public abstract class DefaultAuthent implements Authent{

    private static Logger logger = LoggerFactory.getLogger(DefaultAuthent.class);

    private String LOGIN_USER_PARAM = "Account";
    private String LOGIN_PWD_PARAM = "Password";

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    protected String rootUrl;

    public abstract TaskService getTaskService();

    protected Task task;

    protected Lottery lottery;

    protected UserLottery userLottery;

    public void init(Task task,Lottery lottery,UserLottery userLottery){
        this.task = task;
        this.lottery = lottery;
        this.userLottery = userLottery;
    }
    /**
     * 模拟登陆
     */
    public CookieStore simulateLogin(){
        logger.info("Name=simulateLogin,Desc=模拟登陆,lottery={},userLottery={}",lottery,userLottery);
        long loginTime = System.currentTimeMillis();
        String loginurl = String.format("%s%s%s", rootUrl,lottery.getLoginUrl(),""+loginTime);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(LOGIN_USER_PARAM, userLottery.getLoginUser()));
        params.add(new BasicNameValuePair(LOGIN_PWD_PARAM, userLottery.getLoginPwd()));
        CookieStore cookieStore = doPost(loginurl,params);
        MapUtils.put(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()),cookieStore);
        return cookieStore;
    }



    public CookieStore doPost(String postUrl,List<NameValuePair> parameterList) {
        CookieStore cookieStore = null;
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameterList, "UTF-8");
            httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpPost.setHeader("Accept-Encoding","gzip, deflate");
            httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
            httpPost.setEntity(entity);
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);

            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("retStr value:"+retStr);
            int status = JSONObject.fromObject(retStr).getInt("Status");
            if(status==1){
                cookieStore = setCookieStore(closeableHttpResponse);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
        }
        return cookieStore;
    }


    public CookieStore setCookieStore(HttpResponse httpResponse) {
        String root = rootUrl.replace("http://","");
        CookieStore cookieStore = new BasicCookieStore();
        Header[] headers = httpResponse.getAllHeaders();
        for (Header h : headers) {
            String name = h.getName();
            String value = h.getValue();
            System.out.println("header : " + h.getName() + ":" + h.getValue());
            if ("Set-Cookie".equalsIgnoreCase(name)) {
                String[] strs = value.split(";");
                for (String str : strs) {
                    if("httponly".equals(str.trim().toLowerCase())){
                        continue;
                    }
                    String[] cookies = str.split("=");
//                    for(int n = 0; n<cookies.length;n++){
//                        System.out.println(n+":"+cookies[n]);
//                    }
                    BasicClientCookie cookie = new BasicClientCookie(cookies[0],cookies[1]);
                    cookie.setDomain(root);
                    cookieStore.addCookie(cookie);
//                    BasicClientCookie cookie = new BasicClientCookie(cookies[0],cookies[1]);
//                    cookie.setDomain("k1.cq6055.xyz");
//                    cookieStore.addCookie(cookie);
                }
            }
        }
        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",JSESSIONID);
        cookie.setDomain(root);
        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    /**
     * 模拟同意网站协议
     * @param
     */
    @Override
    public void simulateAgreement() {
        String url = String.format("%s%s%s",rootUrl,lottery.getAgreementUrl(),System.currentTimeMillis());
        logger.info("Name=simulateAgreement,Desc=模拟同意网站协议,url={}",url);
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
        httpGet.setHeader("Referer",rootUrl+"/Member/Agreement");
        httpGet.setHeader("Host",rootUrl.replace("http://",""));
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
//            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            String retStr = EntityUtils.toString(httpEntity, "UTF-8");
            logger.info("name=simulateAgreement,desc=模拟同意网站协议,return value:"+retStr);
            int status = JSONObject.fromObject(retStr).getInt("Status");
            if(status==1){
                changeTaskStatus();
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public CookieStore initCookieStore(boolean need){
        CookieStore cookieStore = (CookieStore)MapUtils.get(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
        if(need==true||cookieStore==null){
            cookieStore = simulateLogin();
            simulateAgreement();
            return cookieStore;
        }else {
            return cookieStore;
        }
    }

    public void changeTaskStatus(){
        Task task1 = new Task();
        task1.setId(task.getId());
        task1.setStatus(TaskStatusEnum.RUNING.getCode());
        getTaskService().updateById(task1);

    }
}
