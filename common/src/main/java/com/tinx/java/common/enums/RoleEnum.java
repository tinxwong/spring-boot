package com.tinx.java.common.enums;

/**
 * @author tinx
 * @date 2018-11-8 16:48
 */
public enum  RoleEnum {

    ADMIN(1,"ADMIN","管理员"),
    MANAGER(3,"MANAGER","平台管理员"),
    COMMON(2,"COMMON","普通用户");

    private int code;
    private String name;

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

    private String desc;

    RoleEnum(int code,String name,String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
