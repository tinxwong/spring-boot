package com.tinx.java.common.core;

import java.lang.annotation.*;

/**
 * @author tinx
 * @date 2018-10-26 0:10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Model {

    /**
     * <p>
     * 实体对应的表名
     * </p>
     */
    String tableName() default "";
}
