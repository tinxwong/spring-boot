package com.tinx.java.permission.controller;


import com.tinx.java.common.controller.BaseController;
import com.tinx.java.common.service.BaseService;
import com.tinx.java.common.service.ModelMsgService;
import com.tinx.java.permission.entity.query.ObjPermissionQuery;
import com.tinx.java.permission.entity.vo.ObjPermissionVo;
import com.tinx.java.permission.service.ObjPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  ǰ�˿�����
 * </p>
 *
 * @author tinx123
 * @since 2018-10-31
 */
@Controller
@RequestMapping("/permission/objPermission")
public class ObjPermissionController extends BaseController<ObjPermissionQuery,ObjPermissionVo>{

    @Autowired
    private ObjPermissionService objPermissionService;
    @Autowired
    private ModelMsgService modelMsgService;

    @Override
    public String getModuleName() {
        return "objPermission";
    }

    @Override
    public BaseService getServiceName() {
        return objPermissionService;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<String> appNames = modelMsgService.selectDistinctAppName();
        modelAndView.addObject("appNames",appNames);
        modelAndView.setViewName(getModuleName()+"/index");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView add(){
        List<String> appNames = modelMsgService.selectDistinctAppName();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("appNames", appNames);
        modelAndView.setViewName(getModuleName() + "/add");
        return modelAndView;
    }

}

