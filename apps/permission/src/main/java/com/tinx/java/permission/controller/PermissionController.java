package com.tinx.java.permission.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import com.tinx.java.common.utils.SpringContextUtils;
import com.tinx.java.common.utils.StringUtil;
import com.tinx.java.permission.entity.ObjPermission;
import com.tinx.java.permission.service.ObjPermissionService;
import com.tinx.java.permission.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author tinx
 * @date 2018-11-8 18:14
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ObjPermissionService objPermissionService;

    @RequestMapping(value = "/getTab", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse getTab(){
        return ResultUtil.makeBaseResponse(permissionService.getTab(), ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/getMenu", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse getMenu(@RequestParam("rootId") Long rootId){
        return ResultUtil.makeBaseResponse(permissionService.getPageMenu(rootId), ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/getOwnUrl", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> getOwnUrl(){
        return permissionService.getOwnUrl();
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse getMenu(){
        return ResultUtil.makeBaseResponse(permissionService.test(), ResponseCode.SUCCESS);
    }


    @RequestMapping(value = "/iap",method = RequestMethod.GET)
    public void initAdminPermission(){
        RequestMappingHandlerMapping rmhp = SpringContextUtils.getApplicationContext().getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
        int i = 0;
        for (RequestMappingInfo info : map.keySet()) {
            String url = info.getPatternsCondition().toString();
            url = url.replace("[","").replace("]","");
            System.out.println(url);
            String[] arrUrl = url.split("/");
            if(arrUrl.length>3){
                if(arrUrl[3].contains("Self")){
                    continue;
                }
                String mdKey = StringUtil.MD5(url);
                try{
                    ObjPermission objPermission = objPermissionService.selectOne(Condition.create().eq("obj_id","1").eq("obj_type","ROLE").eq("md_key",mdKey));
                    if(objPermission!=null){
                        continue;
                    }
                    ObjPermission op = new ObjPermission();
                    op.setObjId(1l);
                    op.setObjType("ROLE");
                    op.setAppName(arrUrl[1]);
                    op.setModuleName(arrUrl[2]);
                    op.setUrl(url);
                    op.setMdKey(mdKey);
                    objPermissionService.insert(op);
                }catch (RuntimeException e){
                    logger.error(e.getMessage());
                }
                i++;
            }

        }
        logger.info("��ʼ����ϣ�����ʼ��{}������",i);
    }

}
