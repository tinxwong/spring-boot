package com.tinx.java.common.entity.query;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("sys_config")
public class SysConfigQuery extends BaseQuery{

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * key前缀
     */
    @TableField("cfg_key_prefix")
    private String cfgKeyPrefix;
    /**
     * key后缀
     */
    @TableField("cfg_key_suffix")
    private String cfgKeySuffix;
    /**
     * 配置值
     */
    @TableField("cfg_value")
    private String cfgValue;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     *是否可用
     */
    protected Integer visibility;

}
