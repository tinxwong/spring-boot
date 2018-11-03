package com.tinx.java.permission.config;

import com.tinx.java.common.mybatis.config.AbstractMybatisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tinx
 * @date 2018-10-26 15:41
 */
@Configuration("permission-mybatis")
@PropertySource(value = {"classpath:config/permission-jdbc.properties"})
public class PermissionConfig extends AbstractMybatisConfig {

    public String getModelName() {
        return "permission";
    }

    public MybatisScannerConfig getMybatisScanConfig() {
        MybatisScannerConfig config = new MybatisScannerConfig();
        config.setBasePackage("com.tinx.java.permission.mapper");
        config.setTypeAliasesPackage("com.tinx.java.permission.entity");
        config.setMapperLocations("classpath*:mapper/permission/*Mapper.xml");
        return config;
    }
}
