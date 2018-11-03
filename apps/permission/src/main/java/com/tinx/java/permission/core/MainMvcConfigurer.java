package com.tinx.java.permission.core;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.permission.interceptor.AccessInterceptor;
import com.tinx.java.permission.entity.SysConfig;
import com.tinx.java.permission.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        // 添加一个拦截器，连接以/admin为前缀的 url路径
        SysConfig sysconfig = sysConfigService.selectOne(Condition.create().eq("cfg_key_prefix","INTERCEPTOR").eq("cfg_key_suffix","APP"));
        if(sysconfig!=null){
            String app = sysconfig.getCfgValue();
            String[] arrApp = app.split(",");
            for(String str : arrApp){
                System.out.println("拦截器:"+str);
                registry.addInterceptor(new AccessInterceptor()).addPathPatterns(String.format("/%s/**",str));
            }
        }


    }
}
