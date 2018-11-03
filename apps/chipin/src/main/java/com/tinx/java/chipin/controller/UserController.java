package com.tinx.java.chipin.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.entity.User;
import com.tinx.java.chipin.entity.query.UserQuery;
import com.tinx.java.chipin.entity.vo.UserVo;
import com.tinx.java.chipin.enums.RoleEnum;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.UserService;
import com.tinx.java.chipin.utils.MyBeanUtils;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
//@Controller
//@RequestMapping("/chipin/user")
public class UserController extends ChipInController<UserQuery,UserVo>{

    @Autowired
    private UserService userService;

    @Override
    public String getModuleName() {
        return "user";
    }

    @Override
    public ChipinService getServiceName() {
        return userService;
    }


    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse list(){
        List<User> list = userService.selectList(Condition.create().eq("role_id", ""+RoleEnum.COMMON.getCode()).eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        List<UserVo> userVos = MyBeanUtils.convertBeanList(list,UserVo.class);
        for(UserVo userVo:userVos){
            System.out.println(userVo);
        }
        return ResultUtil.makeBaseResponse(userVos, ResponseCode.SUCCESS);
    }
}

