package com.tinx.java.chipin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author tinx
 * @date 2018-8-27 22:27
 */

public class ChipinConfiguration implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(ChipinConfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("add interceptors");
        registry.addInterceptor(new LoginRequiredInterceptor()).excludePathPatterns(Arrays.asList("/login", "/res/**","/index"));
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/lottery/resources/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/lottery/resources/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/lottery/resources/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/lottery/resources/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/lottery/resources/plugins/**").addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/lottery/resources/font-awesome/**").addResourceLocations("classpath:/static/font-awesome/");
    }
}
