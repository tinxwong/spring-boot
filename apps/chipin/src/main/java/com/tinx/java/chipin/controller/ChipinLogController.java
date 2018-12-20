package com.tinx.java.chipin.controller;


import com.tinx.java.chipin.entity.query.ChipinLogQuery;
import com.tinx.java.chipin.entity.vo.ChipinLogVo;
import com.tinx.java.chipin.service.ChipinLogService;
import com.tinx.java.common.controller.BaseController;
import com.tinx.java.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tinx123
 * @since 2018-12-10
 */
@Controller
@RequestMapping("/chipin/chipinLog")
public class ChipinLogController extends BaseController<ChipinLogQuery,ChipinLogVo> {

    @Autowired
    private ChipinLogService chipinLogService;

    @Override
    public String getModuleName() {
        return "chipinLog";
    }

    @Override
    public BaseService getServiceName() {
        return chipinLogService;
    }

}

