package com.tinx.java.chipin.enums;

/**
 * @author tinx
 * @date 2018-9-1 23:11
 */
public enum RoleEnum {
    ADMIN(1,"ADMIN","管理员"),
    COMMON(2,"COMMON","普通用户");

    private int code;
    private String name;
    private String desc;

    RoleEnum(int code,String name ,String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
