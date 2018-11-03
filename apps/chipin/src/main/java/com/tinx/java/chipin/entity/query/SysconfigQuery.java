package com.tinx.java.chipin.entity.query;

import com.tinx.java.chipin.entity.ChipinQuery;
import lombok.Data;

import java.util.Date;

/**
 * @author tinx
 * @date 2018-8-21 21:11
 */
@Data
public class SysconfigQuery extends ChipinQuery {
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
