package com.tinx.java.common.response;

import com.tinx.java.common.response.status.interfaces.StatusCode;

/**
 *基础的返回
 * @author tinx

 * 2018年1月26日下午5:02:27
 */
public interface Response<T> {

    StatusCode getCode();

    String getMsg();

    T getData();

    String getCallback();
}
