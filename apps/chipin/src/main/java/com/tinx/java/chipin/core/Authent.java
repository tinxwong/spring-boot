package com.tinx.java.chipin.core;

import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import org.apache.http.client.CookieStore;

/**
 * @author tinx
 * @date 2018-10-7 18:35
 */
public interface Authent {

    /**
     * 模拟登陆
     */
    public CookieStore simulateLogin();
    /**
     * 模拟同意
     */
    public void simulateAgreement();

    /**
     * 获取当前期数
     */
    //public String getCurrentPeriodStatus();

    /**
     * 模拟下注
     * @return
     */
    //public boolean simulateChipin();

    /**
     * 上传文件
     * @return
     */
    //public String uploadBetNos();

    public CookieStore initCookieStore(boolean need);

    public void setRootUrl(String url);

    public void init(Task task, Lottery lottery, UserLottery userLottery);

    public void setTask(Task task);

    public Task getTask();
}
