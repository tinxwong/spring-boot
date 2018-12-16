package com.tinx.java.chipin.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.entity.query.UserLotteryQuery;
import com.tinx.java.chipin.entity.vo.UserLotteryVo;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.chipin.service.UserLotteryService;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.constraint.UrlType;
import com.tinx.java.common.controller.BaseController;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.BaseService;
import com.tinx.java.common.service.UserService;
import com.tinx.java.common.utils.ResultUtil;
import com.tinx.java.common.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tinx123
 * @since 2018-09-01
 */
@Controller
@RequestMapping("/chipin/userLottery")
public class UserLotteryController extends BaseController<UserLotteryQuery,UserLotteryVo> {

    @Autowired
    private UserLotteryService userLotteryService;

    @Autowired
    private UserService userService;

    @Override
    public String getModuleName() {
        return "userLottery";
    }

    @Override
    public BaseService getServiceName() {
        return userLotteryService;
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse del(@RequestParam("id") Long id) {

        return ResultUtil.makeBaseResponse(userLotteryService.deleteById(id), ResponseCode.SUCCESS);
    }


    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST},name= UrlType.URL)
    @ResponseBody
    public BaseResponse save(UserLotteryQuery query) {
        int count = userService.selectCount(Condition.create().eq("id",query.getUserId()).eq("visibility",VisibilityEnum.CAN_USE.getCode()));
        if(count==0){
            return ResultUtil.makeBaseResponse("", ResponseCode.USER_NOT_EXIST);
        }
        List<UserLottery> list = userLotteryService.selectList(Condition.create().eq("user_id",query.getUserId()).eq("lottery_id",query.getLotteryId()).eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        if(list!=null&&list.size()>0){
            return ResultUtil.makeBaseResponse("", ResponseCode.USER_LOTTERY_EXIST);
        }

        return super.save(query);
    }

    @RequestMapping(value = "/listSelfByPage", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
    @ResponseBody
    public CustomPage<UserLotteryVo> listSelfByPage(UserLotteryQuery query){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        query.setUserId(user.getId());
        return getServiceName().selectPageList(query);
    }
}

