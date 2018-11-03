package com.tinx.java.chipin.entity.query;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.tinx.java.chipin.entity.ChipinQuery;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-09-01
 */
@Data
public class UserLotteryQuery extends ChipinQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 网站ID
     */
    private Integer lotteryId;
    /**
     * 登录名
     */
    private String loginUser;
    /**
     * 登录密码
     */
    private String loginPwd;
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
     * 用户名
     */
    private String userName;
    /**
     * 网站名
     */
    private String lotteryName;

    /**
     *是否可用
     */
    private Integer visibility;

}
