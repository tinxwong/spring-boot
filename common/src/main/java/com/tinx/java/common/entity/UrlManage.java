package com.tinx.java.common.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tinx
 * @date 2018-11-7 20:41
 */
@Data
@TableName("url_manage")
public class UrlManage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    private String text;

    private String href;

    @TableField("url_type")
    private String urlType;

    @TableField("parent_id")
    private Long parentId;

    @TableField("md_url")
    private String mdUrl;

    @TableField("app_name")
    private String appName;

    @TableField("root_id")
    private Long rootId;

    @TableField("module_name")
    private String moduleName;

    private Integer visibility;
    @TableField("create_time")
    private String createTime;
    @TableField("create_user")
    private String createUser;

}
