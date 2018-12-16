package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.core.DefaultAuthent;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.query.LotteryQuery;
import com.tinx.java.chipin.entity.vo.LotteryVo;
import com.tinx.java.chipin.mapper.LotteryDao;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.LotteryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ResultUtil;
import com.tinx.java.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Service
public class LotteryServiceImpl extends ServiceImpl<LotteryDao, Lottery> implements LotteryService {

    @Override
    public CustomPage<LotteryVo> selectPageList(LotteryQuery query) {

        PageQuery<Lottery> pagePlus = new PageQuery<Lottery>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Lottery lettery = new Lottery();
        BeanUtils.copyProperties(query,lettery);
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility",query.getVisibility());
        if(StringUtils.isNotEmpty(query.getName())){
            wrapper.like("name",query.getName());
        }
        Page<Lottery> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<Lottery> lotteries = pageList.getRecords();
        List<LotteryVo> lotteryVos = BeanConverter.copy(lotteries,LotteryVo.class);
        CustomPage<LotteryVo> customPage = new CustomPage<LotteryVo>(pageList);
        customPage.setRows(lotteryVos);
        return customPage;
    }

    public CustomPage<LotteryVo> selectRecyclePageList(LotteryQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public Long save(LotteryQuery query) {
        User user = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        Lottery lottery = new Lottery();
        BeanUtils.copyProperties(query,lottery);
        lottery.setCreateUser(user.getUserName());
        this.insert(lottery);
        return lottery.getId();
    }

    @Override
    public boolean editById(LotteryQuery query) {
        Lottery lottery = new Lottery();
        BeanUtils.copyProperties(query, lottery);
        return this.updateById(lottery);
    }

    @Override
    public boolean revById(Long id) {

        Lottery lottery = new Lottery();
        lottery.setId(id);
        lottery.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(lottery);
    }

    public boolean restore(Long id){
        Lottery lottery = new Lottery();
        lottery.setId(id);
        lottery.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(lottery);
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

    public CustomPage<LotteryVo> selectSelfPageList(LotteryQuery query){
        return null;
    }

    public boolean editSelf(LotteryQuery query){
        return false;
    }

    public List<String> getAuthent(){
        Reflections reflections = new Reflections("com.tinx.java.chipin.core");
        Set<Class<? extends DefaultAuthent>> subTypes = reflections.getSubTypesOf(DefaultAuthent.class);
        List<String> list = new ArrayList<>();
        for (Class<? extends DefaultAuthent> clz : subTypes) {
            System.out.println("======"+clz.getName());
            try{
                Method method = clz.getMethod("getAuthentName");
                String authentName = (String)method.invoke(clz.newInstance());
                list.add(authentName);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public boolean copy(Long[] ids){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        for(Long id :ids){
            Lottery lottery = selectOne(Condition.create().eq("id",id).eq("visibility", VisibilityEnum.CAN_USE.getCode()));
            if(lottery!=null){
                lottery.setId(null);
                lottery.setCreateUser(user.getUserName());
                lottery.setUpdateUser(null);
                lottery.setUpdateTime(null);
                insert(lottery);
            }
        }
        return true;
    }

}
