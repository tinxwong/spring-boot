package com.tinx.java.chipin.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.entity.query.UserLotteryQuery;
import com.tinx.java.chipin.entity.vo.UserLotteryVo;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.UserLotteryService;
import com.tinx.java.chipin.service.impl.UserLotteryServiceImpl;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
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
public class UserLotteryController extends ChipInController<UserLotteryQuery,UserLotteryVo> {

    @Autowired
    private UserLotteryService userLotteryService;

    @Override
    public String getModuleName() {
        return "userLottery";
    }

    @Override
    public ChipinService getServiceName() {
        return userLotteryService;
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse del(@RequestParam("id") Long id) {

        return ResultUtil.makeBaseResponse(userLotteryService.deleteById(id), ResponseCode.SUCCESS);
    }


    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse save(UserLotteryQuery query) {

        List<UserLottery> list = userLotteryService.selectList(Condition.create().eq("user_id",query.getUserId()).eq("lottery_id",query.getLotteryId()));
        if(list!=null&&list.size()>0){
            return ResultUtil.makeBaseResponse("", ResponseCode.USER_LOTTERY_EXIST);
        }

        return super.save(query);
    }
}

