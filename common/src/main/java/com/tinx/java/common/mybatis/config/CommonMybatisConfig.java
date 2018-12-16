package com.tinx.java.common.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tinx
 * @date 2018-10-26 15:41
 */
@Configuration("common-mybatis")
@PropertySource(value = {"classpath:config/common-jdbc.properties"})
public class CommonMybatisConfig extends AbstractMybatisConfig {
//public class CommonConfig{
    public String getModelName() {
        return "common";
    }

    public MybatisScannerConfig getMybatisScanConfig() {
        MybatisScannerConfig config = new MybatisScannerConfig();
        config.setBasePackage("com.tinx.java.common.mapper");
        config.setTypeAliasesPackage("com.tinx.java.common.entity");
        config.setMapperLocations("classpath*:mapper/common/*Mapper.xml");
        return config;
    }
}
