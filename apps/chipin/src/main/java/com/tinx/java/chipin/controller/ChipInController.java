package com.tinx.java.chipin.controller;

import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.query.SysconfigQuery;
import com.tinx.java.chipin.entity.vo.SysconfigVo;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @author tinx
 * @date 2018-8-26 22:25
 */
public abstract class ChipInController<Q,V> {

    public abstract String getModuleName();

    public abstract ChipinService getServiceName();

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){

        return getModuleName()+"/index";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){

        return getModuleName()+"/add";
    }

    @RequestMapping(value = "/listByPage", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public CustomPage<V> listByPage(Q query){

        return getServiceName().selectPageList(query);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
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


    @RequestMapping(value = "/editById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse editById(Q query){
        if(getServiceName().editById(query)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }

    @RequestMapping(value="/rev",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse remove(@RequestParam("id") long id){
        if(getServiceName().revById(id)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }

    @RequestMapping(value="/batchRev",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse batchRev(@RequestParam("ids") Long[] ids){
        List<Long> idList = Arrays.asList(ids);
        if(getServiceName().batchRev(idList)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse del(@RequestParam("id")Long id){
        return ResultUtil.makeBaseResponse(getServiceName().deleteById(id), ResponseCode.SUCCESS);
    }

}
