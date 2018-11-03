package com.tinx.java.chipin.thread.entity;

import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import net.sf.json.JSONObject;
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
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tinx
 * @date 2018-9-16 17:41
 */
public class K1Cathectic implements Cathectic{

    private CookieStore cookieStore;

    private Lottery lottery;
    private long loginTime = 0l;
    private static String LOGIN_USER_PARAM = "Account";
    private static String LOGIN_PWD_PARAM = "Password";

    public CookieStore getCookieStore(){
        return cookieStore;
    }

    public K1Cathectic(Lottery lottery){
        this.lottery = lottery;
    }
    @Override
    public void simulateLogin() {
        String url = lottery.getLoginUrl();
        loginTime = System.currentTimeMillis();
        url =url+""+loginTime;
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair(LOGIN_USER_PARAM, "hh0055"));
        params.add(new BasicNameValuePair(LOGIN_PWD_PARAM, "aa841118"));
        doPost(url,params);
    }

    @Override
    public void simulateAgreement() {
        String url = lottery.getAgreementUrl();
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            closeableHttpClient = httpClientBuilder.build();
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");
        httpGet.setHeader("Referer","http://k1.cq6055.xyz/Member/Agreement");
        httpGet.setHeader("Host","k1.cq6055.xyz");
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
//            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            String retStr = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("retStr value:"+retStr);
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCurrentPeriodStatus() {
        String url = lottery.getCurrentPeriodStatusUrl()+System.currentTimeMillis();

        String currPeriodNo = "";
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            closeableHttpClient = httpClientBuilder.build();
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");
        httpGet.setHeader("Referer","http://k3.cq6055.xyz/App/Index?_=1530453466709");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Host","k1.cq6055.xyz");
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
        return currPeriodNo;

    }

    @Override
    public BigDecimal getAccountBalance() {
        String url = lottery.getAccountUrl();
        return null;
    }

    @Override
    public String getPeriodNo() {
        String url = lottery.getPeriodsUrl();
        return null;
    }

    public boolean simulateChipin(){
        String url = lottery.getChipInUrl();
        String periodNo = getCurrentPeriodStatus();
        System.out.println(periodNo);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("bets", "[{\"dict_no_type_id\":\"11\",\"bet_no\":\"1234\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"7\",\"bet_no\":\"123X\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"3\",\"bet_no\":\"4XX5\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"16\",\"bet_no\":\"5XXX5\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"15\",\"bet_no\":\"XXX12\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"18\",\"bet_no\":\"XX1X2\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"17\",\"bet_no\":\"X1XX2\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"2\",\"bet_no\":\"1X1X\",\"bet_money\":\"1\"},{\"dict_no_type_id\":\"1\",\"bet_no\":\"11XX\",\"bet_money\":\"1\"}]"));
        params.add(new BasicNameValuePair("way", "103"));
        params.add(new BasicNameValuePair("guid", "18fa46df-a18c-4cb3-9aad-d40c7a329cff"));
        params.add(new BasicNameValuePair("is_package", "0"));
        params.add(new BasicNameValuePair("period_no", periodNo));
        params.add(new BasicNameValuePair("TotalCount", "9"));
        params.add(new BasicNameValuePair("TotalBetMoney", "0"));
        params.add(new BasicNameValuePair("bet_money", "1"));
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            closeableHttpClient = httpClientBuilder.build();
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
            httpPost.setHeader("Host","k1.cq6055.xyz");
            httpPost.setHeader("Origin","http://k1.cq6055.xyz");
            httpPost.setHeader("Proxy-Connection","keep-alive");
            httpPost.setHeader("Referer","http://k1.cq6055.xyz/App/Index?_="+System.currentTimeMillis());
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
            httpPost.setHeader("X-Requested-With","XMLHttpRequest");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);


//            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("下注结果:"+retStr);
        return true;
    }

    /**
     * 将cookie保存到静态变量中供后续调用
     * @param httpResponse
     */
    public void setCookieStore(HttpResponse httpResponse) {
        cookieStore = new BasicCookieStore();
        Header[] headers = httpResponse.getAllHeaders();
        for (Header h : headers) {
            String name = h.getName();
            String value = h.getValue();
            System.out.println("header : " + h.getName() + ":" + h.getValue());
            if ("Set-Cookie".equalsIgnoreCase(name)) {
                String[] strs = value.split(";");
                for (String str : strs) {
                    if ("HttpOnly".equals(str)) {
                        continue;
                    }
                    String[] cookies = str.split("=");
                    for (int n = 0; n < cookies.length; n++) {
                        System.out.println(n + ":" + cookies[n]);
                    }
                    BasicClientCookie cookie = new BasicClientCookie(cookies[0], cookies[1]);
                    cookie.setDomain("k1.cq6055.xyz");
                    cookieStore.addCookie(cookie);
                }
            }
            String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
            String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
                    setCookie.indexOf(";"));
            BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
            cookie.setDomain("k1.cq6055.xyz");
            cookieStore.addCookie(cookie);
        }
    }

    public String doPost(String postUrl,List<NameValuePair> parameterList) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            closeableHttpClient = httpClientBuilder.build();
        }
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
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);


            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("retStr value:"+retStr);
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
        }
        return retStr;
    }

    public void doGet(String getUrl) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;
        if(cookieStore!=null){
            closeableHttpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        }else{
            closeableHttpClient = httpClientBuilder.build();
        }
        HttpGet httpGet = new HttpGet(getUrl);
        httpGet.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
        httpGet.setHeader("X-Requested-With","XMLHttpRequest");
        httpGet.setHeader("Referer","http://k1.cq6055.xyz/Member/Agreement");
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000).setConnectTimeout(30000).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            setCookieStore(response);

            HttpEntity httpEntity = response.getEntity();
            retStr = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("retStr value:"+retStr);
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
