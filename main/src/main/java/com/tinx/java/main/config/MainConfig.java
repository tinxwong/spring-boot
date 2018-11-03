package com.tinx.java.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author tinx
 * @date 2018-10-29 20:31
 */
@Configuration
@PropertySource(value = {"classpath:config/commons.properties"})
public class MainConfig {
}
