package com.tinx.java.chipin.config;

import com.tinx.java.common.mybatis.config.AbstractMybatisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tinx
 * @date 2018-10-26 15:41
 */
@Configuration("chipin-mybatis")
@PropertySource(value = {"classpath:config/chipin-jdbc.properties"})
public class ChipinConfig extends AbstractMybatisConfig {

    public String getModelName() {
        return "chipin";
    }

    public MybatisScannerConfig getMybatisScanConfig() {
        MybatisScannerConfig config = new MybatisScannerConfig();
        config.setBasePackage("com.tinx.java.chipin.mapper");
        config.setTypeAliasesPackage("com.tinx.java.chipin.entity");
        config.setMapperLocations("classpath*:mapper/chipin/*Mapper.xml");
        return config;
    }
}
