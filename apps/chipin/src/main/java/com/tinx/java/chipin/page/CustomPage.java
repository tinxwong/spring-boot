package com.tinx.java.chipin.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * @author tinx
 * @date 2018-6-10 13:44
 */
public class CustomPage<V> {

    //当前页数
    private int page;

    //每页显示数量
    private int pagesize;

    //总条数
    private int results;

    //数据列表
    private List<V> rows;

    //总页数
    private int total;

    //排序字段
    private String orderByField;

    //是否升序
    private boolean isAsc;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<V> getRows() {
        return rows;
    }

    public void setRows(List<V> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public CustomPage(){}

    public CustomPage(Page page){
        this.page = page.getCurrent();
        this.pagesize = page.getSize();
        this.results = page.getTotal();
        this.total = page.getPages();
        this.orderByField = page.getOrderByField();
        this.isAsc = page.isAsc();

    }
}
