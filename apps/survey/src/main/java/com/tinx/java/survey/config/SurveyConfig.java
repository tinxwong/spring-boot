package com.tinx.java.survey.config;

import com.tinx.java.common.mybatis.config.AbstractMybatisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tinx
 * @date 2018-10-26 15:41
 */
@Configuration("survey-mybatis")
@PropertySource(value = {"classpath:config/survey-jdbc.properties"})
public class SurveyConfig extends AbstractMybatisConfig {

    public String getModelName() {
        return "survey";
    }

    public MybatisScannerConfig getMybatisScanConfig() {
        MybatisScannerConfig config = new MybatisScannerConfig();
        config.setBasePackage("com.tinx.java.survey.mapper");
        config.setTypeAliasesPackage("com.tinx.java.survey.entity");
        config.setMapperLocations("classpath*:mapper/survey/*Mapper.xml");
        return config;
    }
}
