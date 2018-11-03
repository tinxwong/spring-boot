package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.entity.query.UserLotteryQuery;
import com.tinx.java.chipin.entity.vo.UserLotteryVo;
import com.tinx.java.chipin.mapper.UserLotteryDao;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.UserLotteryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-09-01
 */
@Service
public class UserLotteryServiceImpl extends ServiceImpl<UserLotteryDao, UserLottery> implements UserLotteryService {

    @Override
    public CustomPage<UserLotteryVo> selectPageList(UserLotteryQuery query) {

        PageQuery<UserLottery> pagePlus = new PageQuery<UserLottery>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        UserLottery userLottery = new UserLottery();
        BeanUtils.copyProperties(query,userLottery);
        pagePlus.setData(userLottery);

        Page<UserLottery> pageList = selectPage(pagePlus.getPagePlus());
        List<UserLottery> userLotteries = pageList.getRecords();
        List<UserLotteryVo> userLotteryVos = BeanConverter.copy(userLotteries,UserLotteryVo.class);
        CustomPage<UserLotteryVo> customPage = new CustomPage<UserLotteryVo>(pageList);
        customPage.setRows(userLotteryVos);
        return customPage;
    }

    @Override
    public Long save(UserLotteryQuery query) {
        UserLottery userLottery = new UserLottery();
        BeanUtils.copyProperties(query,userLottery);
        this.insert(userLottery);
        return userLottery.getId();
    }

    @Override
    public boolean editById(UserLotteryQuery query) {
        UserLottery userLottery = new UserLottery();
        BeanUtils.copyProperties(query,userLottery);
        return this.updateById(userLottery);
    }

    @Override
    public boolean revById(Long id) {
        UserLottery userLottery = new UserLottery();
        userLottery.setId(id);
        userLottery.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(userLottery);
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


    public UserLottery selectByUserId(Long userId){
        return this.selectOne(Condition.create().eq("user_id",userId));
    }
}
