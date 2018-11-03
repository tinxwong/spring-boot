package com.tinx.java.chipin.entity;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Data
public class Lottery extends ChipinEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 网站名称
     */
    private String name;
    /**
     * 介绍
     */
    private String intro;

    /**
     * 登录链接
     */
    @TableField("login_url")
    private String loginUrl;

    /**
     * 下注链接
     */
    @TableField("chip_in_url")
    private String chipInUrl;
    /**
     * 期数链接
     */
    @TableField("periods_url")
    private String periodsUrl;
    /**
     * 同意链接
     */
    @TableField("agreement_url")
    private String agreementUrl;
    /**
     * 当前期状态
     */
    @TableField("current_period_status_url")
    private String currentPeriodStatusUrl;
    /**
     * 账户链接
     */
    @TableField("account_url")
    private String accountUrl;
    /**
     *往期开奖号码链接
     */
    @TableField("draw_no_table_url")
    private String drawNoTableUrl;

    /**
     *上传文件链接
     */
    @TableField("upload_bet_nos_url")
    private String uploadBetNosUrl;

    /**
     *根链接
     */
    @TableField("root_url")
    private String rootUrl;

    /**
     *认证类名称
     */
    @TableField("authent_class_name")
    private String authentClassName;

    /**
     * 批量下注链接
     */
    @TableField("batch_bet_url")
    private String batchBetUrl;




}