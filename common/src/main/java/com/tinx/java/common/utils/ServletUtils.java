package com.tinx.java.common.utils;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tinx
 * @date 2018-7-4 19:29
 */
public class ServletUtils {

    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static void setReqAttribute(String key,Object value){
        getRequest().setAttribute(key, value);
    }

    public static Object getReqAttribute(String key){
        return getRequest().getAttribute(key);
    }

    public static HttpSession getSession(){return getRequestAttributes().getRequest().getSession();}
}
