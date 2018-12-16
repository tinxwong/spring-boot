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
 * @since 2018-10-29
 */
@TableName("obj_relation")
public class ObjRelation implements Serializable {

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
     * 关联ID
     */
    @TableField("rel_id")
    private Long relId;
    /**
     * 关联类型
     */
    @TableField("rel_type")
    private String relType;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_user")
    private String createUser;


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

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "ObjRelation{" +
        "id=" + id +
        ", objId=" + objId +
        ", objType=" + objType +
        ", relId=" + relId +
        ", relType=" + relType +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        "}";
    }
}
