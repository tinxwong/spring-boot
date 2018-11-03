package com.tinx.java.common.response.enums;

import com.tinx.java.common.response.status.interfaces.StatusCode;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p/>
 * ResponseCode 响应码
 * <p/>
 *
 * @author wenbing.zhang@meicloud.com
 */
public enum ResponseCode implements StatusCode {
    SUCCESS(0, "成功"),
    FAIL(1, "失败");

    private int code;
    private String msg;

    ResponseCode(int value, String text) {
        this.code = value;
        this.msg = text;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return msg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("msg", msg)
                .toString();
    }
}
