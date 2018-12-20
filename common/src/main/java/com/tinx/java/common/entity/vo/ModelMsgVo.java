package com.tinx.java.common.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
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
 * @since 2018-10-29
 */
@Data
@TableName("model_msg")
public class ModelMsgVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Ψһ��ʶ
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
     * url��md5����
     */
    @TableField("md_key")
    private String mdKey;

    /**
     * ģ������
     */
    private String url;
    /**
     * ����ʱ��
     */
    @TableField(value="create_time",fill= FieldFill.INSERT)
    private Date createTime;
    /**
     * ������
     */
    @TableField("create_user")
    private String createUser;


}
