package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.tinx.java.chipin.entity.ChipinLog;
import com.tinx.java.chipin.entity.query.ChipinLogQuery;
import com.tinx.java.chipin.entity.vo.ChipinLogVo;
import com.tinx.java.chipin.mapper.ChipinLogDao;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.ChipinLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.DateUtil;
import com.tinx.java.common.utils.ServletUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-10-15
 */
@Service
public class ChipinLogServiceImpl extends ServiceImpl<ChipinLogDao, ChipinLog> implements ChipinLogService {


    @Override
    public CustomPage<ChipinLogVo> selectPageList(ChipinLogQuery query) {
        PageQuery<ChipinLog> pagePlus = new PageQuery<ChipinLog>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        ChipinLog task = new ChipinLog();
        BeanUtils.copyProperties(query,task);
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility",query.getVisibility());
        if(query.getUserId()!=null){
            wrapper.eq("user_id",query.getUserId());
        }
        if(StringUtils.isNotEmpty(query.getUserName())){
            wrapper.like("user_name",query.getUserName());
        }
        if(query.getTaskId()!=null){
            wrapper.eq("task_id",query.getTaskId());
        }
        if(StringUtils.isNotEmpty(query.getCreateTime())){
            wrapper.between("create_time",query.getCreateTime(), DateUtil.getSpecifiedDayAfter(query.getCreateTime()));
        }

        Page<ChipinLog> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<ChipinLog> chipinLogs = pageList.getRecords();
        List<ChipinLogVo> chipinLogVos = BeanConverter.copy(chipinLogs,ChipinLogVo.class);
        CustomPage<ChipinLogVo> customPage = new CustomPage<ChipinLogVo>(pageList);
        customPage.setRows(chipinLogVos);
        return customPage;
    }

    @Override
    public Long save(ChipinLogQuery query) {
        User user  = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        ChipinLog chipinLog = new ChipinLog();
        BeanUtils.copyProperties(query,chipinLog);
        chipinLog.setCreateUser(user.getUserName());
        chipinLog.setCreateTime(new Date());
        this.insert(chipinLog);
        return chipinLog.getId();
    }

    @Override
    public boolean editById(ChipinLogQuery query) {
        return false;
    }

    @Override
    public boolean editSelf(ChipinLogQuery query) {
        return false;
    }

    @Override
    public boolean revById(Long id) {
        ChipinLog chipinLog = new ChipinLog();
        chipinLog.setId(id);
        chipinLog.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(chipinLog);
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
    public CustomPage<ChipinLogVo> selectSelfPageList(ChipinLogQuery query) {
        User user  = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);

        query.setUserId(user.getId());
        return selectPageList(query);
    }

    @Override
    public CustomPage<ChipinLogVo> selectRecyclePageList(ChipinLogQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public boolean restore(Long id) {
        ChipinLog chipinLog = new ChipinLog();
        chipinLog.setId(id);
        chipinLog.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(chipinLog);
    }
}
