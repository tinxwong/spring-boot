package com.tinx.java.common.enums;

/**
 * @author tinx
 * @date 2018-11-24 12:37
 */
public enum GenderEnum {

    MALE("1","男"),
    FEMALE("2","女");

    private String code;
    private String name;

    GenderEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
