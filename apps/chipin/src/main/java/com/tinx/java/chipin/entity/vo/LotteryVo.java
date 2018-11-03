package com.tinx.java.chipin.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2018-08-11
 */
@Data
public class LotteryVo extends ChipinVo implements Serializable {

    private static final long serialVersionUID = 1L;


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
     *开奖号码链接
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
}
