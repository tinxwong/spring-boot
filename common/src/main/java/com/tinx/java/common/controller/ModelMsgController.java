package com.tinx.java.common.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.entity.ModelMsg;
import com.tinx.java.common.entity.query.ModelMsgQuery;
import com.tinx.java.common.entity.vo.ModelMsgVo;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.ModelMsgService;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author tinx
 * @date 2018-12-7 16:55
 */
@Controller
@RequestMapping("/common/modelmsg")
public class ModelMsgController {

    @Autowired
    private ModelMsgService modelMsgService;

    @RequestMapping(value = "/loadModuleName", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse loadModuleName(@RequestParam("appName")String appName) {
        List<String> list = modelMsgService.selectDistinctModuleNameByAppName(appName);
        return ResultUtil.makeBaseResponse(list, ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/loadByParam", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse loadByAppName(ModelMsgQuery modelMsgQuery) {
        List<ModelMsgVo> list = modelMsgService.selectList(modelMsgQuery.getAppName(),modelMsgQuery.getModuleName());
        return ResultUtil.makeBaseResponse(list, ResponseCode.SUCCESS);
    }
}
