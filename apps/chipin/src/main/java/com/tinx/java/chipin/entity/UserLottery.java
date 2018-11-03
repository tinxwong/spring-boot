package com.tinx.java.chipin.entity;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-09-01
 */
@Data
@TableName("user_lottery")
public class UserLottery extends ChipinEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 网站ID
     */
    @TableField("lottery_id")
    private Integer lotteryId;
    /**
     * 登录名
     */
    @TableField("login_user")
    private String loginUser;
    /**
     * 登录密码
     */
    @TableField("login_pwd")
    private String loginPwd;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 网站名
     */
    @TableField("lottery_name")
    private String lotteryName;


}
