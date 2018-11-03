package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.Sysconfig;
import com.tinx.java.chipin.entity.query.SysconfigQuery;
import com.tinx.java.chipin.entity.vo.SysconfigVo;
import com.tinx.java.chipin.mapper.SysconfigDao;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.SysconfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
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
public class SysconfigServiceImpl extends ServiceImpl<SysconfigDao, Sysconfig> implements SysconfigService {

    public Long save(SysconfigQuery query){
        Sysconfig sysconfig = new Sysconfig();
        BeanUtils.copyProperties(query,sysconfig);
        this.insert(sysconfig);
        return sysconfig.getId();
    }

    public CustomPage<SysconfigVo> selectPageList(SysconfigQuery query){
        PageQuery<Sysconfig> pagePlus = new PageQuery<Sysconfig>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Sysconfig sysconfig = new Sysconfig();
        BeanUtils.copyProperties(query,sysconfig);
        sysconfig.setVisibility(VisibilityEnum.CAN_USE.getCode());
        pagePlus.setData(sysconfig);

        Page<Sysconfig> pageList = selectPage(pagePlus.getPagePlus());
        List<Sysconfig> sysconfigs = pageList.getRecords();
        List<SysconfigVo> sysconfigVos = BeanConverter.copy(sysconfigs,SysconfigVo.class);
        CustomPage<SysconfigVo> customPage = new CustomPage<SysconfigVo>(pageList);
        customPage.setRows(sysconfigVos);
        return customPage;
    }


    public boolean editById(SysconfigQuery query){
        Sysconfig sysconfig = new Sysconfig();
        BeanUtils.copyProperties(query,sysconfig);
        return this.updateById(sysconfig);
    }



    public boolean revById(Long id){
        Sysconfig sysconfig = new Sysconfig();
        sysconfig.setId(id);
        sysconfig.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(sysconfig);
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
    public Sysconfig getValue(String prefix,String suffix){
        return selectOne(Condition.create().eq("cfg_key_suffix",suffix).eq("cfg_key_prefix",prefix));
    }

}
