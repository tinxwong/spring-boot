package com.tinx.java.common.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.common.utils.SpringContextUtils;
import com.tinx.java.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author tinx
 * @date 2018-11-14 18:20
 */
@Controller
@RequestMapping("/common")
public class CommonController {



    @RequestMapping(value = "/index",method = RequestMethod.GET,name="main")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
