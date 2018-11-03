package com.tinx.java.chipin.entity.query;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2018-10-07
 */
@Data
public class UserLotteryRuleQuery extends ChipinEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 网站ID
     */
    private Long lotteryId;
    /**
     * 规则ID
     */
    private Long ruleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 开始期数
     */
    private String startPeriod;
    /**
     * 结束期数
     */
    private String endPeriod;
    /**
     * 间隔期数
     */
    private Integer intervalPeriods;

}
