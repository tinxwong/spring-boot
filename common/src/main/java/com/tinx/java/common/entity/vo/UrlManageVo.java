package com.tinx.java.common.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tinx
 * @date 2018-11-7 20:41
 */
@Data
public class UrlManageVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    protected Long id;

    private String text;

    private String href;

    private String urlType;

    private Long parentId;

    private String mdUrl;

    private String appName;

    private Long rootId;

    private String moduleName;
    /**
     * 创建人
     */
    protected String createUser;
    /**
     * 修改人
     */
    protected String updateUser;
    /**
     * 是否可用
     */
    private Integer visibility;

    private String createTime;
}
