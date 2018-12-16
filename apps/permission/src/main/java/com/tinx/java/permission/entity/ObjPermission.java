package com.tinx.java.permission.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
 * @since 2018-10-31
 */
@Data
@TableName("obj_permission")
public class ObjPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Ψһ��ʶ
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * ����ID
     */
    @TableField("obj_id")
    private Long objId;
    /**
     * ��������
     */
    @TableField("obj_type")
    private String objType;
    /**
     * url��md5����ֵ
     */
    @TableField("md_key")
    private String mdKey;
    /**
     * ����ʱ��
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * ������
     */
    @TableField("create_user")
    private String createUser;
    /**
     * ��������
     */
    private String url;

    /**
     * ��ʶ
     */
    private String instruction;
    /**
     * Ӧ������
     */
    @TableField("app_name")
    private String appName;
    /**
     * ģ������
     */
    @TableField("module_name")
    private String moduleName;
    /**
     * ��������
     */
    @TableField("url_type")
    private String urlType;
    /**
     * CSS����
     */
    @TableField("css_name")
    private String cssName;
    /**
     * ��ʾ����
            */
    @TableField("show_name")
    private String showName;


    private Integer visibility;

}
