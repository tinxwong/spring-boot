package com.tinx.java.chipin.entity.query;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tinx.java.chipin.entity.ChipinEntity;
import com.tinx.java.chipin.entity.ChipinQuery;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-10-15
 */
@Data
public class ChipinLogQuery extends ChipinQuery {


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
    private String chipinTimeScope;
    /**
     * 投注用户名
     */
    private String nextChipinTime;
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

    private String betsContent;

    private int betsSize;

    private Integer visibility;

    private String createTime;

    private String createUser;

}
