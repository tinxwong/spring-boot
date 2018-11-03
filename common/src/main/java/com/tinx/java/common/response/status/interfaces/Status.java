package com.tinx.java.common.response.status.interfaces;

import java.io.Serializable;

/**
 * Created by huangzc8 on 2018/1/17.
 */
public interface Status extends Serializable {

    int code();

    String msg();

}