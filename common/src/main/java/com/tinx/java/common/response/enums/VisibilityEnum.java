package com.tinx.java.common.response.enums;

/**
 * @author tinx
 * @date 2018-8-31 21:45
 */
public enum VisibilityEnum {
    CAN_USE(1,"可用"),
    CAN_NOT_USE(0,"不可用");

    private int code;
    private String name;

    VisibilityEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
