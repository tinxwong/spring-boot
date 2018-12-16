package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.ChipinConfig;
import com.tinx.java.chipin.entity.query.ChipinConfigQuery;
import com.tinx.java.chipin.entity.vo.ChipinConfigVo;
import com.tinx.java.chipin.mapper.ChipinConfigDao;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.ChipinConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Service
public class ChippinConfigServiceImpl extends ServiceImpl<ChipinConfigDao, ChipinConfig> implements ChipinConfigService {

    public Long save(ChipinConfigQuery query){
        ChipinConfig config = new ChipinConfig();
        BeanUtils.copyProperties(query,config);
        this.insert(config);
        return config.getId();
    }

    public CustomPage<ChipinConfigVo> selectPageList(ChipinConfigQuery query){
        PageQuery<ChipinConfig> pagePlus = new PageQuery<ChipinConfig>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility",query.getVisibility());;
        if(StringUtils.isNotEmpty(query.getCfgKeyPrefix())){
            wrapper.eq("cfg_key_prefix",query.getCfgKeyPrefix());
        }
        Page<ChipinConfig> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<ChipinConfig> configs = pageList.getRecords();
        List<ChipinConfigVo> configVos = BeanConverter.copy(configs,ChipinConfigVo.class);
        CustomPage<ChipinConfigVo> customPage = new CustomPage<ChipinConfigVo>(pageList);
        customPage.setRows(configVos);
        return customPage;
    }

    public CustomPage<ChipinConfigVo> selectRecyclePageList(ChipinConfigQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    public boolean editById(ChipinConfigQuery query){
        ChipinConfig config = new ChipinConfig();
        BeanUtils.copyProperties(query,config);
        return this.updateById(config);
    }



    public boolean revById(Long id){
        ChipinConfig config = new ChipinConfig();
        config.setId(id);
        config.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(config);
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
    public ChipinConfig getValue(String prefix,String suffix){
        return selectOne(Condition.create().eq("cfg_key_suffix",suffix).eq("cfg_key_prefix",prefix));
    }

    public CustomPage<ChipinConfigVo> selectSelfPageList(ChipinConfigQuery query){
        return null;
    }

    public boolean editSelf(ChipinConfigQuery query){
        return false;
    }
    public boolean restore(Long id){return false;}
}
