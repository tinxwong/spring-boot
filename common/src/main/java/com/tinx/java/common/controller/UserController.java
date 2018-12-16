package com.tinx.java.common.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.constraint.UrlType;
import com.tinx.java.common.entity.Role;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.entity.query.UserQuery;
import com.tinx.java.common.entity.vo.UserVo;
import com.tinx.java.common.enums.RoleEnum;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.BaseService;
import com.tinx.java.common.service.UserService;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Controller
@RequestMapping("/common/user")
public class UserController extends BaseController<UserQuery,UserVo> {

    @Autowired
    private UserService userService;

    public String getModuleName() {
        return "user";
    }

    @Override
    public BaseService getServiceName() {
        return userService;
    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("modelName", getModuleName());
//        modelAndView.setViewName(getModuleName() + "/index");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String add() {
//
//        return getModuleName() + "/add";
//    }
//
//    @RequestMapping(value = "/listByPage", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public CustomPage<UserVo> listByPage(UserQuery query) {
//
//        return userService.selectPageList(query);
//    }
//
//    @RequestMapping(value = "/listSelfByPage", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public CustomPage<UserVo> listSelfByPage(UserQuery query){
//
//        return userService.selectSelfPageList(query);
//    }
//
//    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public BaseResponse save(UserQuery query) {
//        Long id = null;
//        try {
//            id = userService.save(query);
//        } catch (Exception e) {
//            return ResultUtil.makeBaseResponse("", ResponseCode.USERNAME_UNIQUE_ERROR);
//        }
//
//        if (id > 0) {
//            return ResultUtil.makeBaseResponse(id, ResponseCode.SUCCESS);
//        } else {
//            return ResultUtil.makeBaseResponse(null, ResponseCode.ERROR);
//        }
//    }
//
//
//    @RequestMapping(value = "/editById", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public BaseResponse editById(UserQuery query) {
//        if (userService.editById(query)) {
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        } else {
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/rev", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse remove(@RequestParam("id") long id) {
//        if (userService.revById(id)) {
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        } else {
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/batchRev", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse batchRev(@RequestParam("ids") Long[] ids) {
//        List<Long> idList = Arrays.asList(ids);
//        if (userService.batchRev(idList)) {
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        } else {
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/del", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse del(@RequestParam("id") Long id) {
//        return ResultUtil.makeBaseResponse(userService.deleteById(id), ResponseCode.SUCCESS);
//    }

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
//
//    @RequestMapping(value = "/editSelf", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public BaseResponse editSelf(UserQuery query){
//        if(userService.editSelf(query)){
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        }else{
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }


    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST},name= UrlType.URL)
    @ResponseBody
    public BaseResponse list(){
        List<User> list = userService.selectList(Condition.create().eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        List<UserVo> userVos = BeanConverter.copy(list,UserVo.class);
        return ResultUtil.makeBaseResponse(userVos, ResponseCode.SUCCESS);
    }
}

