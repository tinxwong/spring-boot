package com.tinx.java.chipin.entity.query;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.tinx.java.chipin.entity.ChipinQuery;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Data
public class LotteryQuery extends ChipinQuery {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Long id;
    /**
     * 登录链接
     */
    private String loginUrl;

    /**
     * 下注链接
     */
    private String chipInUrl;
    /**
     * 期数链接
     */
    private String periodsUrl;
    /**
     * 账户链接
     */
    private String accountUrl;
    /**
     * 网站名称
     */
    private String name;
    /**
     * 介绍
     */
    private String intro;
    /**
     *是否可用
     */
    private Integer visibility;
    /**
     * 同意链接
     */
    private String agreementUrl;
    /**
     * 当前期状态
     */
    private String currentPeriodStatusUrl;
    /**
     *往期开奖号码链接
     */
    private String drawNoTableUrl;
    /**
     *根链接
     */
    private String rootUrl;

    /**
     *上传文件
     */
    private String uploadBetNosUrl;

    /**
     *认证类名称
     */
    private String authentClassName;

    /**
     * 批量下注链接
     */
    private String batchBetUrl;
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



}
