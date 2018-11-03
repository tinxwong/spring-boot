package com.tinx.java.chipin.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tinx.java.chipin.entity.ChipinEntity;
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
public class ChipinLogVo extends ChipinVo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 投注用户ID
     */
    private Long lotteryUserId;
    /**
     * 投注用户名
     */
    private String lotteryUserName;
    /**
     * 任务ID
     */
    private Long taskId;
    /**
     * 投注内容
     */
    private String bets;
    /**
     * 单注金额
     */
    private String betMoney;
    /**
     * 全部金额
     */
    private String totalBetMoney;
    /**
     * 投注码数量
     */
    private String betCount;
    /**
     * 期数
     */
    private String periodNo;
    /**
     * 执行结果
     */
    private String results;

    /**
     * 账户余额
     */
    private String accountBalance;


}
