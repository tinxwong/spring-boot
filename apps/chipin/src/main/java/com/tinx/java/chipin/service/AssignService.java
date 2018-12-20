package com.tinx.java.chipin.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.core.DefaultAuthent;
import com.tinx.java.chipin.core.rule.ChipinRule;
import com.tinx.java.chipin.core.rule.Rule;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.RuleBean;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.UserService;
import com.tinx.java.common.utils.ResultUtil;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author tinx
 * @date 2018-12-5 22:45
 */
@Service
public class AssignService {

    @Autowired
    private UserService userService;

    @Autowired
    private LotteryService lotteryService;


    public List<RuleBean> getRule(){
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
        return rules;
    }

    public List<String> getAuthent(){
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
        return list;
    }

    public List<User> getUsers(){
        return userService.selectList(Condition.create().eq("visibility", VisibilityEnum.CAN_USE.getCode()));
    }

    public List<Lottery> getLotterys(){
        return lotteryService.selectList(Condition.create().eq("visibility", VisibilityEnum.CAN_USE.getCode()));
    }
}
