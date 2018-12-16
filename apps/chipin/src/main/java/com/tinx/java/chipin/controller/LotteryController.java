package com.tinx.java.chipin.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.chipin.core.DefaultAuthent;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.query.LotteryQuery;
import com.tinx.java.chipin.entity.vo.LotteryVo;
import com.tinx.java.chipin.service.AssignService;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.LotteryService;
import com.tinx.java.chipin.utils.MyBeanUtils;
import com.tinx.java.common.constraint.UrlType;
import com.tinx.java.common.controller.BaseController;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.BaseService;
import com.tinx.java.common.utils.ResultUtil;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class LotteryController extends BaseController<LotteryQuery,LotteryVo> {

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private AssignService assignService;
    @Override
    public String getModuleName() {
        return "lottery";
    }

    @Override
    public BaseService getServiceName() {
        return lotteryService;
    }



    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST},name= UrlType.URL)
    @ResponseBody
    public BaseResponse list(){
        List<Lottery> list = lotteryService.selectList(Condition.create().eq("visibility", VisibilityEnum.CAN_USE.getCode()));
        List<LotteryVo> lotteryVos = MyBeanUtils.convertBeanList(list,LotteryVo.class);
        return ResultUtil.makeBaseResponse(lotteryVos, ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST,name= UrlType.BUTTON)
    @ResponseBody
    public BaseResponse copy(@RequestParam("ids") Long[] ids){
        return ResultUtil.makeBaseResponse(lotteryService.copy(ids), ResponseCode.SUCCESS);

    }

    @RequestMapping(value = "/index",method = RequestMethod.GET,name= UrlType.CHANNEL)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<String> authents = assignService.getAuthent();
        modelAndView.addObject("authents",authents);
        modelAndView.setViewName(getModuleName()+"/index");
        return modelAndView;
    }


}

