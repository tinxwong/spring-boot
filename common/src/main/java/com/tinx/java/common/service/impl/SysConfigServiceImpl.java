package com.tinx.java.common.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.SysConfig;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.entity.query.SysConfigQuery;
import com.tinx.java.common.entity.vo.SysConfigVo;
import com.tinx.java.common.mapper.SysConfigDao;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.common.page.PageQuery;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.service.SysConfigService;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-10-29
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfig> implements SysConfigService {

    @Override
    public CustomPage<SysConfigVo> selectPageList(SysConfigQuery query) {
        PageQuery<SysConfig> pagePlus = new PageQuery<SysConfig>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility", query.getVisibility());
        if(query.getId()!=null){
            wrapper.eq("id",query.getId());
        }
        if(StringUtils.isNotEmpty(query.getCfgKeyPrefix())){
            wrapper.eq("cfg_key_prefix",query.getCfgKeyPrefix());
        }
        Page<SysConfig> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<SysConfig> sysConfigs = pageList.getRecords();
        List<SysConfigVo> sysConfigVos = BeanConverter.copy(sysConfigs,SysConfigVo.class);
        CustomPage<SysConfigVo> customPage = new CustomPage<SysConfigVo>(pageList);
        customPage.setRows(sysConfigVos);
        return customPage;
    }

    @Override
    public CustomPage<SysConfigVo> selectSelfPageList(SysConfigQuery query) {
       return null;
    }

    @Override
    public CustomPage<SysConfigVo> selectRecyclePageList(SysConfigQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public boolean restore(Long id) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setId(id);
        sysConfig.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(sysConfig);
    }

    @Override
    public Long save(SysConfigQuery query) {
        User user = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        SysConfig sysConfig = new SysConfig();
        BeanConverter.copyProperties(query,sysConfig);
        sysConfig.setVisibility(VisibilityEnum.CAN_USE.getCode());
        sysConfig.setCreateUser(user.getUserName());
        insert(sysConfig);
        return sysConfig.getId();
    }

    @Override
    public boolean editById(SysConfigQuery query) {
        SysConfig sysConfig = new SysConfig();
        BeanConverter.copyProperties(query,sysConfig);
        return updateById(sysConfig);
    }

    @Override
    public boolean revById(Long id) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setId(id);
        sysConfig.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(sysConfig);
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
    public boolean editSelf(SysConfigQuery query) {
        return false;
    }
}
