package com.tinx.java.common.entity.query;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

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
public class ModelMsgQuery extends BaseQuery {

    private static final long serialVersionUID = 1L;

    /**
     * Ψһ��ʶ
     */
    private Long id;
    /**
     * Ӧ������
     */
    private String appName;
    /**
     * ģ������
     */
    private String moduleName;

    /**
     * url��md5����
     */
    private String mdKey;

    /**
     * ģ������
     */
    private String url;
    /**
     * ����ʱ��
     */
    private Date createTime;
    /**
     * ������
     */
    private String createUser;

    private Integer visibility;


}
