package com.tinx.java.permission.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-10-29
 */
@Data
@TableName("model_msg")
public class ModelMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 应用名称
     */
    @TableField("app_name")
    private String appName;
    /**
     * 模块名称
     */
    @TableField("module_name")
    private String moduleName;
    /**
     * url的md5加密
     */
    @TableField("md_key")
    private String mdKey;
    /**
     * 模块链接
     */
    private String url;
    /**
     * 创建时间
     */
    @TableField(value="create_time",fill= FieldFill.INSERT)
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;


}
