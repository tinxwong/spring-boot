package com.tinx.java.chipin.entity;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Data
public class Sysconfig  extends ChipinEntity {

    private static final long serialVersionUID = 1L;

    /**
     * key名后缀
     */
    @TableField("cfg_key_suffix")
    private String cfgKeySuffix;
    /**
     * key名前缀
     */
    @TableField("cfg_key_prefix")
    private String cfgKeyPrefix;
    /**
     * key的值
     */
    @TableField("cfg_value")
    private String cfgValue;


}
