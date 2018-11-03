package com.tinx.java.chipin.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.tinx.java.chipin.enums.RoleEnum;
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
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Integer id;
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
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     *是否可用
     */
    private Integer visibility;

    private Integer roleId;

    private String roleDesc;

    public String getRoleDesc(){
        for(RoleEnum roleEnum:RoleEnum.values()){
            System.out.println("getRoleId().intValue():"+getRoleId().intValue());
            System.out.println("roleEnum.getCode():"+roleEnum.getCode());
            if(roleEnum.getCode()==getRoleId().intValue()){
                return roleEnum.getDesc();
            }
        }
        return "";
    }
}
