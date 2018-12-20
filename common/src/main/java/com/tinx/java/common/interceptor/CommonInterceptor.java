package com.tinx.java.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author tinx
 * @date 2018-11-4 19:46
 */
public class CommonInterceptor implements HandlerInterceptor {

    protected Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("CommonInterceptor preHandle!");
        String url = request.getRequestURI();
        System.out.println("url:"+url);

        HttpSession session = request.getSession();
        if (session.getAttribute(Helper.SESSION_USER) == null) {
            System.out.println("AuthorizationException:未登录！"+request.getMethod());
            response.sendRedirect(request.getContextPath()+"/common/ver/login");

            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("CommonInterceptor postHandle");
        boolean isAdmin = false;
//        Set<String> roleIds = (Set<String>)request.getSession().getAttribute(Helper.SESSION_USER_ROLE_SET);
//        for(String roleId : roleIds){
//            if("1".equals(roleId)){
//                isAdmin = true;
//            }
//        }
//        if(!isAdmin){
//            if(modelAndView!=null){
//                Set<String> urls = (Set<String>)request.getSession().getAttribute(Helper.SESSION_USER_ACCESS_URL_SET);
//                modelAndView.addObject("USER_ACCESS_URL",urls);
//            }
//        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
