package com.tinx.java.permission.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.common.page.PageQuery;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.common.utils.StringUtil;
import com.tinx.java.permission.entity.ObjPermission;
import com.tinx.java.permission.entity.query.ObjPermissionQuery;
import com.tinx.java.permission.entity.vo.ObjPermissionVo;
import com.tinx.java.permission.mapper.ObjPermissionDao;
import com.tinx.java.permission.service.ObjPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  ����ʵ����
 * </p>
 *
 * @author tinx123
 * @since 2018-10-31
 */
@Service
public class ObjPermissionServiceImpl extends ServiceImpl<ObjPermissionDao, ObjPermission> implements ObjPermissionService {

    @Override
    public CustomPage<ObjPermissionVo> selectPageList(ObjPermissionQuery query) {

        PageQuery<ObjPermission> pagePlus = new PageQuery<ObjPermission>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        ObjPermission objPermission = new ObjPermission();
        BeanUtils.copyProperties(query,objPermission);
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility",query.getVisibility());
        if(StringUtils.isNotEmpty(query.getUrl())){
            wrapper.like("url",query.getUrl());
        }
        if(StringUtils.isNotEmpty(query.getAppName())){
            wrapper.eq("app_name",query.getAppName());
        }
        if(StringUtils.isNotEmpty(query.getModuleName())){
            wrapper.eq("module_name",query.getModuleName());
        }
        Page<ObjPermission> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<ObjPermission> userLotteries = pageList.getRecords();
        List<ObjPermissionVo> userLotteryVos = BeanConverter.copy(userLotteries,ObjPermissionVo.class);
        CustomPage<ObjPermissionVo> customPage = new CustomPage<ObjPermissionVo>(pageList);
        customPage.setRows(userLotteryVos);
        return customPage;
    }

    @Override
    public Long save(ObjPermissionQuery query) {
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        ObjPermission objPermission = new ObjPermission();
        BeanUtils.copyProperties(query,objPermission);
        if("all".equals(objPermission.getUrl())){
            List<ObjPermission> list = selectList(Condition.create().eq("app_name",query.getAppName()).eq("module_name",query.getModuleName()).eq("obj_id","1").eq("obj_type","ROLE").eq("visibility",VisibilityEnum.CAN_USE.getCode()));
            List<ObjPermission> objPermissions = new ArrayList<>();
            for(ObjPermission obj:list){
                ObjPermission op = new ObjPermission();
                BeanConverter.copyProperties(obj,op);
                obj.setId(null);
                obj.setObjId(query.getObjId());
                obj.setObjType(query.getObjType());
                obj.setCreateUser(user.getUserName());
                obj.setCreateTime(new Date());
                objPermissions.add(obj);
            }
            if(insertBatch(objPermissions)){
                return 1l;
            }else{
                return 0l;
            }
        }else{
            objPermission.setMdKey(StringUtil.MD5(objPermission.getUrl()));
            this.insert(objPermission);
            return objPermission.getId();
        }

    }

    @Override
    public boolean editById(ObjPermissionQuery query) {
        ObjPermission objPermission = new ObjPermission();
        BeanUtils.copyProperties(query,objPermission);
        return this.updateById(objPermission);
    }

    @Override
    public boolean revById(Long id) {
        ObjPermission objPermission = new ObjPermission();
        objPermission.setId(id);
        objPermission.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(objPermission);
    }

    @Transactional
    public boolean batchRev(List<Long> idList) {
        try{
            for(Long id : idList){
                revById(id);
            }
        }catch (RuntimeException e){
            return false;
        }

        return true;
    }



    public CustomPage<ObjPermissionVo> selectSelfPageList(ObjPermissionQuery query){
        User user  = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        PageQuery<ObjPermission> pagePlus = new PageQuery<ObjPermission>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        ObjPermission objPermission = new ObjPermission();
        BeanUtils.copyProperties(query,objPermission);
        pagePlus.setData(objPermission);

        Page<ObjPermission> pageList = selectPage(pagePlus.getPagePlus());
        List<ObjPermission> userLotteries = pageList.getRecords();
        List<ObjPermissionVo> userLotteryVos = BeanConverter.copy(userLotteries,ObjPermissionVo.class);
        CustomPage<ObjPermissionVo> customPage = new CustomPage<ObjPermissionVo>(pageList);
        customPage.setRows(userLotteryVos);
        return customPage;
    }

    @Override
    public CustomPage<ObjPermissionVo> selectRecyclePageList(ObjPermissionQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public boolean restore(Long id) {
        ObjPermission objPermission = new ObjPermission();
        objPermission.setId(id);
        objPermission.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(objPermission);
    }

    public boolean editSelf(ObjPermissionQuery query){
        return false;
    }
}
