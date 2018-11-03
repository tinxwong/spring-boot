package com.tinx.java.main.config;

import com.tinx.java.common.mybatis.config.AbstractMybatisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tinx
 * @date 2018-10-26 17:31
 */
@Configuration
@PropertySource(value = {"classpath:config/main-jdbc.properties"})
public class MainMybatisConfig extends AbstractMybatisConfig{

    public String getModelName() {
        return "main";
    }

    public AbstractMybatisConfig.MybatisScannerConfig getMybatisScanConfig() {
        AbstractMybatisConfig.MybatisScannerConfig config = new AbstractMybatisConfig.MybatisScannerConfig();
        config.setBasePackage("com.tinx.java.main.mapper");
        config.setTypeAliasesPackage("com.tinx.java.main.entity");
        config.setMapperLocations("classpath*:mapper/main/*Mapper.xml");
        return config;
    }
}
