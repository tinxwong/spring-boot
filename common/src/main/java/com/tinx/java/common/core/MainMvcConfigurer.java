package com.tinx.java.common.core;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.entity.SysConfig;
import com.tinx.java.common.interceptor.CommonInterceptor;
import com.tinx.java.common.service.SysConfigService;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author tinx
 * @date 2018-10-29 18:26
 */
@Configuration
public class MainMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private SysConfigService sysConfigService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Reflections reflections = new Reflections("com.tinx.java");
        Set<Class<? extends HandlerInterceptor>> subTypes = reflections.getSubTypesOf(HandlerInterceptor.class);
        SysConfig sysConfig = sysConfigService.selectOne(Condition.create().eq("cfg_key_prefix","INTERCEPTOR").eq("cfg_key_suffix","APP"));
        List<String> list = new ArrayList<>();
        if(sysConfig!=null&&!StringUtils.isEmpty(sysConfig.getCfgValue())){
            String[] arrValue = sysConfig.getCfgValue().split(",");

            for(String str :arrValue){
                String value = String.format("/%s/**",str);
                System.out.println("XXXXXXX:"+value);
                list.add(value);
            }
        }

        list.add("/static/**");
        list.add("/assets/**");
        list.add("/common/ver/**");
        for(Class<? extends HandlerInterceptor> clz : subTypes){
            try {
                registry.addInterceptor(clz.newInstance()).excludePathPatterns(list);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 将/** 目录下的静态资源映射到classpath:/view/下
         * 直接访问http://127.0.0.1:8080/index_view.html，发现图片信息获取不到需要增加默认的资源映射
         * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
         * /resources/, /static/, /public/].
         */

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        /**
         * 将/view2/** 目录映射到classpath:/view2下
         * 这种方式不影响springboot默认的静态资源映射路径，可以注释掉以上代码测试
         */
//        registry.addResourceHandler("/view2/**").addResourceLocations(
//                "classpath:/view2/");
    }

}
