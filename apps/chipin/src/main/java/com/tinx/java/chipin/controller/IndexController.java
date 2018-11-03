package com.tinx.java.chipin.controller;

import com.tinx.java.chipin.core.DefaultAuthent;
import com.tinx.java.chipin.core.rule.ChipinRule;
import com.tinx.java.chipin.core.rule.Rule;
import com.tinx.java.chipin.entity.Role;
import com.tinx.java.chipin.entity.RuleBean;
import com.tinx.java.chipin.enums.RoleEnum;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import org.reflections.Reflections;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author tinx
 * @date 2018-8-7 0:07
 */
@Controller
@RequestMapping("/chipin")
public class IndexController extends ChipInController{


    @Override
    public String getModuleName() {
        return "";
    }

    @Override
    public ChipinService getServiceName() {
        return null;
    }

    @RequestMapping(value="/getAuthent",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getAuthent(){
        Reflections reflections = new Reflections("com.tinx.java.chipin.core");
        Set<Class<? extends DefaultAuthent>> subTypes = reflections.getSubTypesOf(DefaultAuthent.class);
        List<String> list = new ArrayList<>();
        for (Class<? extends DefaultAuthent> clz : subTypes) {
            System.out.println("======"+clz.getName());
            try{
                Method method = clz.getMethod("getAuthentName");
                String authentName = (String)method.invoke(clz.newInstance());
                list.add(authentName);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        return ResultUtil.makeBaseResponse(list, ResponseCode.SUCCESS);
    }


    @RequestMapping(value="/getRule",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getRule(){
        Reflections reflections = new Reflections("com.tinx.java.chipin.core");
        Set<Class<? extends ChipinRule>> subTypes = reflections.getSubTypesOf(ChipinRule.class);
        List<RuleBean> rules = new ArrayList<>();
        for (Class<? extends Rule> clz : subTypes) {
            RuleBean rule = new RuleBean();
            System.out.println("======"+clz.getName());
            if(clz.getName().contains("AbstractChipinRule")){
                continue;
            }
            try{
                Method method = clz.getMethod("getRuleName");
                String ruleName = (String)method.invoke(clz.newInstance());
                rule.setRuleName(ruleName);
                method = clz.getMethod("getRuleId");
                Integer ruleId = (Integer)method.invoke(clz.newInstance());
                rule.setRuleId(ruleId);
                method = clz.getMethod("getRuleDesc");
                String ruleDesc = (String)method.invoke(clz.newInstance());
                rule.setRuleDesc(ruleDesc);
                rules.add(rule);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        return ResultUtil.makeBaseResponse(rules, ResponseCode.SUCCESS);
    }

    @RequestMapping(value="/getRoles",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getRoles(){

        List<Role> roles = new ArrayList<>();
        for(RoleEnum roleEnum:RoleEnum.values()){
            Role role = new Role();

            role.setRoleId(roleEnum.getCode());
            role.setRoleName(roleEnum.getName());
            role.setRoleDesc(roleEnum.getDesc());
            roles.add(role);
        }
        return ResultUtil.makeBaseResponse(roles, ResponseCode.SUCCESS);
    }
}
