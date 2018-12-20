package com.tinx.java.common.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

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
@TableName("sys_config")
public class SysConfigVo implements Serializable {

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCfgKeyPrefix() {
        return cfgKeyPrefix;
    }

    public void setCfgKeyPrefix(String cfgKeyPrefix) {
        this.cfgKeyPrefix = cfgKeyPrefix;
    }

    public String getCfgKeySuffix() {
        return cfgKeySuffix;
    }

    public void setCfgKeySuffix(String cfgKeySuffix) {
        this.cfgKeySuffix = cfgKeySuffix;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
        "id=" + id +
        ", cfgKeyPrefix=" + cfgKeyPrefix +
        ", cfgKeySuffix=" + cfgKeySuffix +
        ", cfgValue=" + cfgValue +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        "}";
    }
}
