package com.tinx.java.common.mybatis.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 * @author tinx
 * @date 2018-10-26 0:06
 */
public class DataMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        setFieldValByName("createdTime", now, metaObject);
        setFieldValByName("updatedTime", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        setFieldValByName("updatedTime", now, metaObject);
    }
}
