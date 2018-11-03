package com.tinx.java.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author tinx
 * @date 2018-10-25 9:22
 */
@Controller
@RequestMapping("/controllerReflect")
public class ControllerReflect {
    @ResponseBody
    @RequestMapping("/getUrlMapping")
    public Object getUrlMapping(HttpServletRequest request) {
        WebApplicationContext wc = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
        RequestMappingHandlerMapping rmhp = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            System.out.println(info.getPatternsCondition().toString()
                    + ","
                    + map.get(info).getBean().toString());
        }
        return null;

    }
}