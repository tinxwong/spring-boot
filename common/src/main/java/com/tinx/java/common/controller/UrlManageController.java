package com.tinx.java.common.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.entity.ModelMsg;
import com.tinx.java.common.entity.UrlManage;
import com.tinx.java.common.entity.query.SysConfigQuery;
import com.tinx.java.common.entity.query.UrlManageQuery;
import com.tinx.java.common.entity.vo.SysConfigVo;
import com.tinx.java.common.entity.vo.UrlManageVo;
import com.tinx.java.common.enums.UrlTypeEnum;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.BaseService;
import com.tinx.java.common.service.ModelMsgService;
import com.tinx.java.common.service.SysConfigService;
import com.tinx.java.common.service.UrlManageService;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tinx
 * @date 2018-11-24 19:20
 */
@Controller
@RequestMapping("/common/urlmanage")
public class UrlManageController extends BaseController<UrlManageQuery,UrlManageVo>{

    @Autowired
    private UrlManageService urlManageService;


    public String getModuleName() {
        return "urlmanage";
    }

    @Override
    public BaseService getServiceName() {
        return urlManageService;
    }
//
//    @RequestMapping(value = "/index",method = RequestMethod.GET)
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName(getModuleName()+"/index");
//        return modelAndView;
//    }

    @RequestMapping(value = "/loadSub", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse loadSub(@RequestParam("rootId")Long rootId) {
        List<UrlManage> list = urlManageService.selectList(Condition.create().eq("root_id",rootId).eq("url_type", UrlTypeEnum.CHANNEL.getCode()).eq("visibility", VisibilityEnum.CAN_USE.getCode()).eq("parent_id",0));
        return ResultUtil.makeBaseResponse(list, ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        List<UrlManage> urlManageList = urlManageService.loadTabUrlManage();
        modelAndView.addObject("urlManages",urlManageList);
        modelAndView.setViewName(getModuleName() + "/add");
        return modelAndView;
    }

//    @RequestMapping(value = "/listByPage", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public CustomPage<SysConfigVo> listByPage(SysConfigQuery query) {
//
//        return sysconfigService.selectPageList(query);
//    }
//
//    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public BaseResponse save(SysConfigQuery query) {
//        Long id = null;
//        try {
//            id = sysconfigService.save(query);
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
//    public BaseResponse editById(SysConfigQuery query) {
//        if (sysconfigService.editById(query)) {
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        } else {
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/rev", method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse remove(@RequestParam("id") long id) {
//        if (sysconfigService.revById(id)) {
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
//        if (sysconfigService.batchRev(idList)) {
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        } else {
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
}
