package com.tinx.java.chipin.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.common.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 投注任务
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Data
public class TaskVo extends ChipinVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 投注网站ID
     */
    private Long lotteryId;
    /**
     * 最大金额
     */
    private BigDecimal accountMaxLimit;
    /**
     * 最小金额
     */
    private BigDecimal accountMinLimit;
    /**
     * 任务间隔时间
     */
    private Integer intervalTime;
    /**
     * 投注金额
     */
    private BigDecimal money;
    /**
     * 登录名称
     */
    private String lotteryUser;
    /**
     * 登录密码
     */
    private String lotteryPwd;

    /**
     *是否可用
     */
    private Integer visibility;

    private Integer status;

    /**
     * 规则ID
     */
    private String ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     *规则细节
     */
    private String ruleDetail;

    private String ruleDesc;

    /**
     *用户名
     */
    private String userName;
    /**
     *投注网站名称
     */
    private String lotteryName;
    /**
     * 下注文件路径
     */
    private String chipinFilePath;
    /**
     *期数数量
     */
    private Integer periodNum;

    public String getStatusDesc(){
        return TaskStatusEnum.getDesc(getStatus());
    }


}
