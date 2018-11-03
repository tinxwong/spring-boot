package com.tinx.java.chipin.enums;

/**
 * @author tinx
 * @date 2018-9-1 23:10
 */
public enum SexEnum {

    MALE(1,"男"),
    FALALE(2,"女");

    private int code;
    private String name;
    SexEnum(int code,String name){
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
