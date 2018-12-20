package com.tinx.java.common.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.UrlManage;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.entity.query.UrlManageQuery;
import com.tinx.java.common.entity.vo.FirstLevel;
import com.tinx.java.common.entity.vo.SecondLevel;
import com.tinx.java.common.entity.vo.ThridLevel;
import com.tinx.java.common.entity.vo.UrlManageVo;
import com.tinx.java.common.enums.RoleEnum;
import com.tinx.java.common.enums.UrlTypeEnum;
import com.tinx.java.common.mapper.UrlManageDao;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.common.page.PageQuery;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.service.UrlManageService;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.common.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tinx
 * @date 2018-11-7 20:49
 */
@Service
public class UrlManageServiceImpl extends ServiceImpl<UrlManageDao, UrlManage> implements UrlManageService {


    public List<UrlManage> loadTabUrlManage(){
        return selectList(Condition.create().eq("url_type", UrlTypeEnum.TAB.getCode()).eq("visibility",VisibilityEnum.CAN_USE.getCode()));
    }

    public List<UrlManage> loadUrlManagesByRootId(Long rootId){
        return  selectList(Condition.create().eq("root_id",rootId).eq("url_type",UrlTypeEnum.CHANNEL.getCode()).eq("visibility",VisibilityEnum.CAN_USE.getCode()));
    }

    public List<UrlManage> loadButtonUrlManage(String appName,String moduleName){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        return selectList(Condition.create().eq("app_name",appName).eq("module_name",moduleName).eq("url_type",UrlTypeEnum.BUTTON.getCode()).eq("visibility",VisibilityEnum.CAN_USE.getCode()));
    }

    public List<UrlManage> loadUrlUrlManage(String appName,String moduleName){
        return selectList(Condition.create().eq("app_name",appName).eq("module_name",moduleName).eq("url_type",UrlTypeEnum.URL.getCode()).eq("visibility",VisibilityEnum.CAN_USE.getCode()));
    }

    @Override
    public CustomPage<UrlManageVo> selectPageList(UrlManageQuery query) {
        PageQuery<UrlManage> pagePlus = new PageQuery<UrlManage>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility", query.getVisibility());
        if(StringUtils.isNotEmpty(query.getAppName())){
            wrapper.eq("app_name",query.getAppName());
        }
        Page<UrlManage> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<UrlManage> urlManages = pageList.getRecords();
        List<UrlManageVo> urlManageVos = BeanConverter.copy(urlManages,UrlManageVo.class);
        CustomPage<UrlManageVo> customPage = new CustomPage<UrlManageVo>(pageList);
        customPage.setRows(urlManageVos);
        return customPage;
    }

    @Override
    public Long save(UrlManageQuery query) {
        User user = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        UrlManage urlManage = new UrlManage();
        BeanConverter.copyProperties(query,urlManage);
        urlManage.setVisibility(VisibilityEnum.CAN_USE.getCode());
        urlManage.setMdUrl(StringUtil.MD5(query.getHref()));
        insert(urlManage);
        return urlManage.getId();
    }

    @Override
    public boolean editById(UrlManageQuery query) {
        UrlManage urlManage = new UrlManage();
        BeanConverter.copyProperties(query,urlManage);
        return updateById(urlManage);
    }

    @Override
    public boolean editSelf(UrlManageQuery query) {
        return false;
    }

    @Override
    public boolean revById(Long id) {
        UrlManage urlManage = new UrlManage();
        urlManage.setId(id);
        urlManage.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(urlManage);
    }

    @Override
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

    @Override
    public CustomPage<UrlManageVo> selectSelfPageList(UrlManageQuery query) {
        return null;
    }

    @Override
    public CustomPage<UrlManageVo> selectRecyclePageList(UrlManageQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public boolean restore(Long id) {

        UrlManage urlManage = new UrlManage();
        urlManage.setId(id);
        urlManage.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(urlManage);
    }

}
