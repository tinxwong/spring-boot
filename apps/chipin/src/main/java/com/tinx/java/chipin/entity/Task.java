package com.tinx.java.chipin.entity;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 投注任务
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Data
public class Task extends ChipinEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 投注网站ID
     */
    @TableField("lottery_id")
    private Long lotteryId;
    /**
     * 最大金额
     */
    @TableField("account_max_limit")
    private BigDecimal accountMaxLimit;
    /**
     * 最小金额
     */
    @TableField("account_min_limit")
    private BigDecimal accountMinLimit;
    /**
     * 任务间隔时间
     */
    @TableField("interval_time")
    private Integer intervalTime;
    /**
     * 投注金额
     */
    private BigDecimal money;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 下注文件路径
     */
    @TableField("chipin_file_path")
    private String chipinFilePath;


    /**
     * 规则ID
     */
    @TableField("rule_id")
    private String ruleId;

    /**
     * 规则名称
     */
    @TableField("rule_name")
    private String ruleName;

    /**
     *规则细节
     */
    @TableField("rule_detail")
    private String ruleDetail;
    /**
     *规则描述
     */
    @TableField("rule_desc")
    private String ruleDesc;
    /**
     *用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     *投注网站名称
     */
    @TableField("lottery_name")
    private String lotteryName;
    /**
     *期数数量
     */
    @TableField("period_num")
    private Integer periodNum;

    @TableField("interval_periods")
    private BigDecimal intervalPeriods;


}
