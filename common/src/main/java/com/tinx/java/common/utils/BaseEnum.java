package com.tinx.java.common.utils;

import java.io.Serializable;

/**
 * 枚举基本接口
 * createTime 2017-08-23 16:08
 *
 * @author 陆国鸿
 */
public interface BaseEnum<C> extends Serializable {

    /**
     * 枚举编码
     * @return C
     */
    C code();

    /**
     * 枚举消息（说明）
     * @return String
     */
    String msg();

}
