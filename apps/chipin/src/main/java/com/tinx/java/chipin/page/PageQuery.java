package com.tinx.java.chipin.page;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author tinx
 * @date 2018-6-10 11:15
 */
public class PageQuery<T> {


    //时间戳（毫秒）
    private String nd;

    //每页显示条数
    private int limit=10;

    //当前页数
    private int pageIndex=0;

    //排序的字段
    private String sidx;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //排序方式 asc升序  desc降序
    private String sord="asc";


    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex+1;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    //获取mybatisPlus封装的Page对象
    public Page<T> getPagePlus(){
        Page<T> pagePlus = new Page<T>(this.pageIndex,this.limit);
//        pagePlus.setCurrent(this.pageIndex);
//        pagePlus.setSize(this.limit);
//        pagePlus.setAsc(this.sord.equals("asc"));
//        pagePlus.setOrderByField(this.sidx);
        return pagePlus;
    }
}
