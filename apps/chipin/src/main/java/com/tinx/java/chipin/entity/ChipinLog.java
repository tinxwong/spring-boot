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
 * @since 2018-10-15
 */
@Data
@TableName("chipin_log")
public class ChipinLog extends ChipinEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 投注用户ID
     */
    @TableField("chipin_time_scope")
    private String chipinTimeScope;
    /**
     * 投注用户名
     */
    @TableField("next_chipin_time")
    private String nextChipinTime;
    /**
     * 任务ID
     */
    @TableField("task_id")
    private Long taskId;
    /**
     * 投注内容
     */
    private String bets;
    /**
     * 单注金额
     */
    @TableField("bet_money")
    private String betMoney;
    /**
     * 全部金额
     */
    @TableField("total_bet_money")
    private String totalBetMoney;
    /**
     * 投注码数量
     */
    @TableField("bet_count")
    private String betCount;
    /**
     * 期数
     */
    @TableField("period_no")
    private String periodNo;
    /**
     * 执行结果
     */
    private String results;
    /**
     * 账户余额
     */
    @TableField("account_balance")
    private String accountBalance;

    private String betsContent;

    private int betsSize;


}
