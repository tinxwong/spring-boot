package com.tinx.java.chipin.controller;


import com.tinx.java.chipin.entity.ChipinConfig;
import com.tinx.java.chipin.entity.query.ChipinConfigQuery;
import com.tinx.java.chipin.entity.vo.ChipinConfigVo;
import com.tinx.java.chipin.service.ChipinConfigService;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.common.controller.BaseController;
import com.tinx.java.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/chipin/config")
public class ChipinConfigController extends BaseController<ChipinConfigQuery,ChipinConfigVo> {



    @Override
    public String getModuleName() {
        return "config";
    }

    @Override
    public BaseService<ChipinConfigQuery, ChipinConfig, ChipinConfigVo> getServiceName() {
        return chipinConfigService;
    }

    @Autowired
    private ChipinConfigService chipinConfigService;

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

