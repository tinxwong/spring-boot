package com.tinx.java.chipin.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.User;
import com.tinx.java.chipin.entity.query.LotteryQuery;
import com.tinx.java.chipin.entity.vo.LotteryVo;
import com.tinx.java.chipin.entity.vo.UserVo;
import com.tinx.java.chipin.enums.RoleEnum;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.LotteryService;
import com.tinx.java.chipin.service.impl.LotteryServiceImpl;
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
 *  前端控制器
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Controller
@RequestMapping("/chipin/lottery")
public class LotteryController extends ChipInController<LotteryQuery,LotteryVo>{

    @Autowired
    private LotteryService lotteryService;
    @Override
    public String getModuleName() {
        return "lottery";
    }

    @Override
    public ChipinService getServiceName() {
        return lotteryService;
    }



    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse list(){
        List<Lottery> list = lotteryService.selectList(Condition.create().eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        List<LotteryVo> lotteryVos = MyBeanUtils.convertBeanList(list,LotteryVo.class);
        return ResultUtil.makeBaseResponse(lotteryVos, ResponseCode.SUCCESS);
    }
}

