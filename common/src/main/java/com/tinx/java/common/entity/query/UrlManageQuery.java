package com.tinx.java.common.entity.query;

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
@TableName("url_manage")
public class UrlManageQuery extends BaseQuery {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    private String text;

    private String href;

    private String urlType;

    private Long parentId;

    private String mdUrl;

    private String appName;

    private Long rootId;

    private String moduleName;

    private Integer visibility;

}
