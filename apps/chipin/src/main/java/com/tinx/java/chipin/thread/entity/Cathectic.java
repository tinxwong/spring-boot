package com.tinx.java.chipin.thread.entity;

import org.apache.http.client.CookieStore;

import java.math.BigDecimal;

/**
 * @author tinx
 * @date 2018-9-16 17:41
 */
public interface Cathectic {

    public CookieStore getCookieStore();

    public void simulateLogin();

    public void simulateAgreement();

    public String getCurrentPeriodStatus();

    public BigDecimal getAccountBalance();

    public String getPeriodNo();

    public boolean simulateChipin();

}
