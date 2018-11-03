package com.tinx.java.chipin.controller;


import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.Sysconfig;
import com.tinx.java.chipin.entity.query.SysconfigQuery;
import com.tinx.java.chipin.entity.vo.SysconfigVo;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.SysconfigService;
import com.tinx.java.chipin.service.impl.SysconfigServiceImpl;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
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
@RequestMapping("/chipin/sysconfig")
public class SysconfigController extends ChipInController<SysconfigQuery,SysconfigVo>{



    @Override
    public String getModuleName() {
        return "sysconfig";
    }

    @Override
    public ChipinService<SysconfigQuery, Sysconfig, SysconfigVo> getServiceName() {
        return sysconfigService;
    }

    @Autowired
    private SysconfigService sysconfigService;

//
//    @RequestMapping(value = "/listByPage", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public CustomPage<SysconfigVo> listByPage(SysconfigQuery query){
//        return sysconfigService.selectPageList(query);
//    }
//
//    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public BaseResponse save(SysconfigQuery query){
//        Long id = sysconfigService.save(query);
//        if(id>0){
//            return ResultUtil.makeBaseResponse(id, ResponseCode.SUCCESS);
//        }else{
//            return ResultUtil.makeBaseResponse(null, ResponseCode.ERROR);
//        }
//    }
//
//
//    @RequestMapping(value = "/editById", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public BaseResponse editById(SysconfigQuery query){
//        if(sysconfigService.editById(query)){
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        }else{
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
//
//    @RequestMapping(value="/rev",method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse remove(@RequestParam("id") long id){
//        if(sysconfigService.revById(id)){
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        }else{
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
//
//    @RequestMapping(value="/batchRev",method = RequestMethod.POST)
//    @ResponseBody
//    public BaseResponse batchRev(@RequestParam("ids") Long[] ids){
//        List<Long> idList = Arrays.asList(ids);
//        if(sysconfigService.batchRev(idList)){
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        }else{
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }

}

