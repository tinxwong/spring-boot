package com.tinx.java.chipin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tinx
 * @date 2018-8-26 17:06
 */
@Data
public class ChipinQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    //每页显示条数
    protected int limit=10;

    //当前页数
    protected int pageIndex=0;
}
