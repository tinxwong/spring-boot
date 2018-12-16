package com.tinx.java.chipin.controller;

import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.common.constraint.UrlType;
import com.tinx.java.common.enums.UrlTypeEnum;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author tinx
 * @date 2018-8-26 22:25
 */
@Deprecated
public abstract class ChipInController<Q,V> {

    public abstract String getModuleName();

    public abstract ChipinService getServiceName();

    @RequestMapping(value = "/index",method = RequestMethod.GET,name= UrlType.CHANNEL)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("modelName",getModuleName());
        modelAndView.setViewName(getModuleName()+"/index");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET,name=UrlType.BUTTON)
    public ModelAndView add(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getModuleName()+"/add");
        return modelAndView;
    }

    @RequestMapping(value = "/listByPage", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
    @ResponseBody
    public CustomPage<V> listByPage(Q query){

        return getServiceName().selectPageList(query);
    }

    @RequestMapping(value = "/listSelfByPage", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
    @ResponseBody
    public CustomPage<V> listSelfByPage(Q query){
        return getServiceName().selectSelfPageList(query);
    }

    @RequestMapping(value = "/listRecycleByPage", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
    @ResponseBody
    public CustomPage<V> listRecycleByPage(Q query){
        return getServiceName().selectRecyclePageList(query);
    }


    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.BUTTON)
    @ResponseBody
    public BaseResponse save(Q query){
        Long id = null;
        try{
            id = getServiceName().save(query);
        }catch (Exception e){
            return ResultUtil.makeBaseResponse("", ResponseCode.USERNAME_UNIQUE_ERROR);
        }

        if(id>0){
            return ResultUtil.makeBaseResponse(id, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(null, ResponseCode.ERROR);
        }
    }


    @RequestMapping(value = "/editById", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
    @ResponseBody
    public BaseResponse editById(Q query){
        if(getServiceName().editById(query)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }



    @RequestMapping(value = "/editSelf", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
    @ResponseBody
    public BaseResponse editSelf(Q query){
        if(getServiceName().editSelf(query)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }

    @RequestMapping(value="/rev",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
    public BaseResponse remove(@RequestParam("id") long id){
        if(getServiceName().revById(id)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }

    @RequestMapping(value="/batchRev",method = RequestMethod.POST,name=UrlType.BUTTON)
    @ResponseBody
    public BaseResponse batchRev(@RequestParam("ids") Long[] ids){
        List<Long> idList = Arrays.asList(ids);
        if(getServiceName().batchRev(idList)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }


    @RequestMapping(value="/batchDel",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse batchDel(@RequestParam("ids") Long[] ids){
        List<Long> idList = Arrays.asList(ids);
        if(getServiceName().deleteBatchIds(idList)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }

    @RequestMapping(value = "/del",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
    public BaseResponse del(@RequestParam("id")Long id){
        return ResultUtil.makeBaseResponse(getServiceName().deleteById(id), ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/restore",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
    public BaseResponse restore(@RequestParam("id")Long id){
        return ResultUtil.makeBaseResponse(getServiceName().restore(id), ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/indexRecycle",method = RequestMethod.GET,name= UrlType.URL)
    public ModelAndView indexRecycle(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getModuleName()+"/indexRecycle");
        return modelAndView;
    }

}
