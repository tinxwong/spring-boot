package com.tinx.java.permission.entity.vo;

import com.tinx.java.common.enums.UrlTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tinx
 * @date 2018-11-12 22:54
 */
@Data
public class ObjPermissionVo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private Long objId;

    private String objType;

    private String mdKey;

    private Date createTime;

    private String createUser;

    private String url;


    private String instruction;

    private String appName;

    private String moduleName;

    private String urlType;

    private String cssName;

    private String showName;


    private Integer visibility;

    public String getUrlType(){
        return UrlTypeEnum.getDesc(this.urlType);
    }
}
