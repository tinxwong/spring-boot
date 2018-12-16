package com.tinx.java.permission.interceptor;

import com.alibaba.fastjson.JSON;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.common.utils.SpringContextUtils;
import com.tinx.java.common.utils.StringUtil;
import com.tinx.java.permission.entity.Button;
import com.tinx.java.permission.entity.Url;
import com.tinx.java.permission.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tinx
 * @date 2018-10-29 18:29
 */
public class PermissionInterceptor implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(PermissionInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("PermissionInterceptor preHandle!");

        User user = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        if(user!=null){
            String url = request.getRequestURI();
            System.out.println("url:"+url);
            System.out.println(StringUtil.MD5(url));
            PermissionService permissionService = (PermissionService)SpringContextUtils.getBean("permissionServiceImpl");
            int count = permissionService.checkUserRight(url);
            if(count>0){
                return true;
            }else{
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(ResponseCode.USER_NOT_RIGHT));
                out.flush();
                out.close();
                return false;
            }
        }
        return true;
//        HttpSession session = request.getSession();
//        if (session.getAttribute(Helper.SESSION_USER) == null) {
//            System.out.println("AuthorizationException:δ��¼��"+request.getMethod());
//            if("POST".equalsIgnoreCase(request.getMethod())){
//                response.setContentType("text/html; charset=utf-8");
//                PrintWriter out = response.getWriter();
//                out.write(JSON.toJSONString(ResponseCode.USER_NOT_LOGIN));
//                out.flush();
//                out.close();
//            }else{
//                response.sendRedirect(request.getContextPath()+"/login");
//            }
//            return false;
//        } else {
//
//            String url = request.getRequestURI();
//            System.out.println("url");
//            String rs = "";
//            if(rs.trim().isEmpty()){
//                return true;//����ͨ��
//            }else{
//
//                return false;
//            }
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PermissionInterceptor postHandle");
        boolean isAdmin = false;
        PermissionService permissionService = (PermissionService)SpringContextUtils.getBean("permissionServiceImpl");
        String url = request.getRequestURI();
        String[] arrUrl = url.split("/");
        if(arrUrl.length>3){
            System.out.println("arrUrl[3]:"+arrUrl[3]);
            if("index".equals(arrUrl[3])||"indexRecycle".equals(arrUrl[3])){
                List<Button> buttonList = permissionService.loadButton(arrUrl[1],arrUrl[2]);
                modelAndView.addObject("buttons",buttonList);
                List<Url> urls = permissionService.loadUrl(arrUrl[1],arrUrl[2]);
                Map<String,String> map = new HashMap<>();
                for(Url myUrl:urls){
                    map.put(myUrl.getInstruction(),myUrl.getUrl());
                }
                modelAndView.addObject("urls",map);
            }

        }


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
