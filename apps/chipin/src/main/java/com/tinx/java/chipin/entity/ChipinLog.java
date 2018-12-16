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
 * @since 2018-12-10
 */
@Data
@TableName("chipin_log")
public class ChipinLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
    @TableField("lottery_user_id")
    private Long lotteryUserId;
    /**
     * 投注用户名
     */
    @TableField("lottery_user_name")
    private String lotteryUserName;
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
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 结果
     */
    private String results;
    /**
     * 账户余额
     */
    @TableField("account_balance")
    private String accountBalance;
    /**
     * 执行规则名称
     */
    @TableField("execute_rule_name")
    private String executeRuleName;

    private Integer visibility;

    @TableField("bets_content")
    private String betsContent;

    @TableField("bets_size")
    private Integer betsSize;

    @TableField("chipin_time_scope")
    private String chipinTimeScope;

    @TableField("next_chipin_time")
    private String nextChipinTime;
}
