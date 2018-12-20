package com.tinx.java.common.service.impl;

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
import com.tinx.java.common.entity.ModelMsg;
import com.tinx.java.common.entity.query.ModelMsgQuery;
import com.tinx.java.common.entity.vo.ModelMsgVo;
import com.tinx.java.common.mapper.ModelMsgDao;
import com.tinx.java.common.service.ModelMsgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  ����ʵ����
 * </p>
 *
 * @author tinx123
 * @since 2018-10-29
 */
@Service
public class ModelMsgServiceImpl extends ServiceImpl<ModelMsgDao, ModelMsg> implements ModelMsgService {


    public List<String> selectDistinctAppName(){

        List<Map<String,String>> list = selectMaps(Condition.create().setSqlSelect("distinct(app_name) as app_name"));
        List<String> appNames = new ArrayList<>(list.size());
        for(Map<String,String> map :list) {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                appNames.add(entry.getValue());
            }
        }

        return appNames;
    }


    public List<String> selectDistinctModuleNameByAppName(String appName){
        List<Map<String,String>> list = selectMaps(Condition.create().setSqlSelect("distinct(module_name) as module_name").eq("app_name",appName));
        List<String> moduleNames = new ArrayList<>(list.size());
        for(Map<String,String> map :list) {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                moduleNames.add(entry.getValue());
            }
        }

        return moduleNames;
    }

    public List<ModelMsgVo> selectList(String appName,String moduleName){
        Wrapper wrapper = Condition.create();
        if(StringUtils.isNotEmpty(appName)){
            wrapper.eq("app_name",appName);
        }
        if(StringUtils.isNotEmpty(moduleName)){
            wrapper.eq("module_name",moduleName);
        }
        List<ModelMsg> list = selectList(wrapper);
        List<ModelMsgVo> modelMsgVos = BeanConverter.copy(list,ModelMsgVo.class);
        return modelMsgVos;
    }

    @Override
    public CustomPage<ModelMsgVo> selectPageList(ModelMsgQuery query) {

        PageQuery<ModelMsg> pagePlus = new PageQuery<ModelMsg>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        ModelMsg modelMsg = new ModelMsg();
        BeanUtils.copyProperties(query,modelMsg);
        pagePlus.setData(modelMsg);

        Page<ModelMsg> pageList = selectPage(pagePlus.getPagePlus());
        List<ModelMsg> modelMsgs = pageList.getRecords();
        List<ModelMsgVo> modelMsgVos = BeanConverter.copy(modelMsgs,ModelMsgVo.class);
        CustomPage<ModelMsgVo> customPage = new CustomPage<ModelMsgVo>(pageList);
        customPage.setRows(modelMsgVos);
        return customPage;
    }

    @Override
    public Long save(ModelMsgQuery query) {
        ModelMsg modelMsg = new ModelMsg();
        BeanUtils.copyProperties(query,modelMsg);
        this.insert(modelMsg);
        return modelMsg.getId();
    }

    @Override
    public boolean editById(ModelMsgQuery query) {
        ModelMsg modelMsg = new ModelMsg();
        BeanUtils.copyProperties(query,modelMsg);
        return this.updateById(modelMsg);
    }

    @Override
    public boolean revById(Long id) {
        ModelMsg modelMsg = new ModelMsg();
        modelMsg.setId(id);
        modelMsg.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(modelMsg);
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



    public CustomPage<ModelMsgVo> selectSelfPageList(ModelMsgQuery query){
        User user  = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        PageQuery<ModelMsg> pagePlus = new PageQuery<ModelMsg>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        ModelMsg modelMsg = new ModelMsg();
        BeanUtils.copyProperties(query,modelMsg);
        pagePlus.setData(modelMsg);

        Page<ModelMsg> pageList = selectPage(pagePlus.getPagePlus());
        List<ModelMsg> modelMsgs = pageList.getRecords();
        List<ModelMsgVo> modelMsgVos = BeanConverter.copy(modelMsgs,ModelMsgVo.class);
        CustomPage<ModelMsgVo> customPage = new CustomPage<ModelMsgVo>(pageList);
        customPage.setRows(modelMsgVos);
        return customPage;
    }

    @Override
    public CustomPage<ModelMsgVo> selectRecyclePageList(ModelMsgQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public boolean restore(Long id) {
        ModelMsg modelMsg = new ModelMsg();
        modelMsg.setId(id);
        modelMsg.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(modelMsg);
    }

    public boolean editSelf(ModelMsgQuery query){
        return false;
    }
}
