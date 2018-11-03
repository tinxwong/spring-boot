package com.tinx.java.chipin.entity;

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
 * @since 2018-10-07
 */
@Data
@TableName("user_lottery_rule")
public class UserLotteryRule extends ChipinEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 网站ID
     */
    @TableField("lottery_id")
    private Long lotteryId;
    /**
     * 规则ID
     */
    @TableField("rule_id")
    private Long ruleId;
    /**
     * 规则名称
     */
    @TableField("rule_name")
    private String ruleName;
    /**
     * 开始期数
     */
    @TableField("start_period")
    private String startPeriod;
    /**
     * 结束期数
     */
    @TableField("end_period")
    private String endPeriod;
    /**
     * 间隔期数
     */
    @TableField("interval_periods")
    private Integer intervalPeriods;



}
