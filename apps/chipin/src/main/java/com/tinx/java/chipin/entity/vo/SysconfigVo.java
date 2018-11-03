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
public class SysconfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private Long id;
    /**
     * key名后缀
     */
    private String cfgKeySuffix;
    /**
     * key名前缀
     */
    private String cfgKeyPrefix;
    /**
     * key的值
     */
    private String cfgValue;

    /**
     *是否可用
     */
    private Integer visibility;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private Date udateTime;


}
