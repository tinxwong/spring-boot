package com.tinx.java.permission.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

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
@TableName("obj_permission")
public class ObjPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 对象ID
     */
    @TableField("obj_id")
    private Long objId;
    /**
     * 对象类型
     */
    @TableField("obj_type")
    private String objType;
    /**
     * url的md5加密值
     */
    @TableField("md_key")
    private String mdKey;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private byte[] createUser;
    /**
     * 访问链接
     */
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getMdKey() {
        return mdKey;
    }

    public void setMdKey(String mdKey) {
        this.mdKey = mdKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getCreateUser() {
        return createUser;
    }

    public void setCreateUser(byte[] createUser) {
        this.createUser = createUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ObjPermission{" +
        "id=" + id +
        ", objId=" + objId +
        ", objType=" + objType +
        ", mdKey=" + mdKey +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", url=" + url +
        "}";
    }
}
