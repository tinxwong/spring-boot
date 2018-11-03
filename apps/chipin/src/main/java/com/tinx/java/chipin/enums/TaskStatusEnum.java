package com.tinx.java.chipin.enums;

/**
 * @author tinx
 * @date 2018-9-9 22:25
 */
public enum TaskStatusEnum {

    RUNING(1,"运行中"),
    STOP(0,"停止");

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private TaskStatusEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(int code){
        for(TaskStatusEnum taskStatusEnum:TaskStatusEnum.values()){
            if(taskStatusEnum.getCode()==code){
                return taskStatusEnum.getDesc();
            }
        }
        return "";
    }
}
