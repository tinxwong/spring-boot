package com.tinx.java.chipin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tinx
 * @date 2018-9-1 18:47
 */
@Data
public class ChipinEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    /**
     *是否可用
     */
    @TableField("visibility")
    protected Integer visibility;

    /**
     * 创建时间
     */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    protected Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    protected String createUser;
    /**
     * 修改人
     */
    @TableField("update_user")
    protected String updateUser;
    /**
     * 修改时间
     */
    @TableField(value="update_time",fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;
}
