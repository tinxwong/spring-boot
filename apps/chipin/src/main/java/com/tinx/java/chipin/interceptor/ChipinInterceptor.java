package com.tinx.java.chipin.interceptor;

import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.interceptor.CommonInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author tinx
 * @date 2018-10-29 18:29
 */
public class ChipinInterceptor implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(ChipinInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("ChipinInterceptor preHandle");
        String url = request.getRequestURI();
        System.out.println("url:"+url);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("ChipinInterceptor postHandle");


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
