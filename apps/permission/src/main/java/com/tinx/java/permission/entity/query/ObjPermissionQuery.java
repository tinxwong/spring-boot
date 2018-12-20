package com.tinx.java.permission.entity.query;

import com.tinx.java.common.entity.query.BaseQuery;
import lombok.Data;

import java.util.Date;

/**
 * @author tinx
 * @date 2018-11-12 22:54
 */
@Data
public class ObjPermissionQuery extends BaseQuery {

    private Long id;

    private Long objId;

    private String objType;

    private String mdKey;

    private Date createTime;

    private byte[] createUser;

    private String url;

    private String instruction;

    private String appName;

    private String moduleName;

    private String cssName;

    private String showName;

    private Integer visibility;

    private String urlType;
}
