package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.query.LotteryQuery;
import com.tinx.java.chipin.entity.vo.LotteryVo;
import com.tinx.java.chipin.mapper.LotteryDao;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.LotteryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class LotteryServiceImpl extends ServiceImpl<LotteryDao, Lottery> implements LotteryService {

    @Override
    public CustomPage<LotteryVo> selectPageList(LotteryQuery query) {

        PageQuery<Lottery> pagePlus = new PageQuery<Lottery>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Lottery lettery = new Lottery();
        BeanUtils.copyProperties(query,lettery);
        lettery.setVisibility(VisibilityEnum.CAN_USE.getCode());
        pagePlus.setData(lettery);

        Page<Lottery> pageList = selectPage(pagePlus.getPagePlus());
        List<Lottery> lotteries = pageList.getRecords();
        List<LotteryVo> lotteryVos = BeanConverter.copy(lotteries,LotteryVo.class);
        CustomPage<LotteryVo> customPage = new CustomPage<LotteryVo>(pageList);
        customPage.setRows(lotteryVos);
        return customPage;
    }

    @Override
    public Long save(LotteryQuery query) {
        Lottery lottery = new Lottery();
        BeanUtils.copyProperties(query,lottery);
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





}
