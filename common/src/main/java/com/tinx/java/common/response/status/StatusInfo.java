package com.tinx.java.common.response.status;

import com.tinx.java.common.response.status.interfaces.Status;
import com.tinx.java.common.response.status.interfaces.StatusCode;

/**
 * @author tinx
 * @date 2018-10-26 16:28
 */
public class StatusInfo implements Status, StatusCode {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public StatusInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
