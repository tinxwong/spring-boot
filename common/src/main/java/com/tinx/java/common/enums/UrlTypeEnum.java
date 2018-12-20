package com.tinx.java.common.enums;

/**
 * @author tinx
 * @date 2018-11-7 21:08
 */
public enum UrlTypeEnum {
    TAB("1","tab","标签"),
    CHANNEL("2","channel","栏目"),
    BUTTON("3","button","按钮"),
    URL("4","url","链接");

    private String code;
    private String name;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    UrlTypeEnum(String code,String name,String desc){
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static String getName(String code){
        for(UrlTypeEnum urlTypeEnum:UrlTypeEnum.values()){
            if(urlTypeEnum.getCode().equals(code)){
                return urlTypeEnum.getName();
            }
        }
        return "";
    }

    public static String getDesc(String code){
        for(UrlTypeEnum urlTypeEnum:UrlTypeEnum.values()){
            if(urlTypeEnum.getCode().equals(code)){
                return urlTypeEnum.getDesc();
            }
        }
        return "";
    }
}
