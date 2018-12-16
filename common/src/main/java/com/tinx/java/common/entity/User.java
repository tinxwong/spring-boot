package com.tinx.java.common.entity;

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
 * 用户
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Data
@TableName("user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    /**
     *是否可用
     */
    @TableField("visibility")
    protected Integer visibility;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 真实名
     */
    @TableField("true_name")
    private String trueName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 电话
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 创建时间
     */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    protected Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    protected String createUser;
    /**
     * 修改人
     */
    @TableField("update_user")
    protected String updateUser;
    /**
     * 修改时间
     */
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

}
