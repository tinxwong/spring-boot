package com.tinx.java.common.entity.query;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class UserQuery extends BaseQuery{

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    protected Long id;

    /**
     *是否可用
     */
    protected Integer visibility;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 真实名
     */
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
    private String roleName;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 创建时间
     */
    protected Date createTime;
    /**
     * 创建人
     */
    protected String createUser;
    /**
     * 修改人
     */
    protected String updateUser;
    /**
     * 修改时间
     */
    protected Date updateTime;

    protected String checkCode;

}
