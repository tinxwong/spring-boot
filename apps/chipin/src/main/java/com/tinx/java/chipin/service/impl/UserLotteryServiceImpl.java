package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.entity.query.UserLotteryQuery;
import com.tinx.java.chipin.entity.vo.UserLotteryVo;
import com.tinx.java.chipin.mapper.UserLotteryDao;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.UserLotteryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
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
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility",query.getVisibility());
        if(query.getUserId()!=null){
            wrapper.eq("user_id",query.getUserId());
        }
        if(StringUtils.isNotEmpty(query.getUserName())){
            wrapper.eq("user_name",query.getUserName());
        }
        Page<UserLottery> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<UserLottery> userLotteries = pageList.getRecords();
        List<UserLotteryVo> userLotteryVos = BeanConverter.copy(userLotteries,UserLotteryVo.class);
        CustomPage<UserLotteryVo> customPage = new CustomPage<UserLotteryVo>(pageList);
        customPage.setRows(userLotteryVos);
        return customPage;
    }

    @Override
    public Long save(UserLotteryQuery query) {
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        UserLottery userLottery = new UserLottery();
        BeanUtils.copyProperties(query,userLottery);
        userLottery.setCreateUser(user.getUserName());
        this.insert(userLottery);
        return userLottery.getId();
    }

    @Override
    public boolean editById(UserLotteryQuery query) {
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        UserLottery userLottery = new UserLottery();
        BeanUtils.copyProperties(query,userLottery);
        userLottery.setUpdateUser(user.getUserName());
        return this.updateById(userLottery);
    }

    @Override
    public boolean revById(Long id) {
        UserLottery userLottery = new UserLottery();
        userLottery.setId(id);
        userLottery.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(userLottery);
    }

    public boolean restore(Long id){
        UserLottery userLottery = new UserLottery();
        userLottery.setId(id);
        userLottery.setVisibility(VisibilityEnum.CAN_USE.getCode());
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


    public UserLottery selectByParam(Long userId,Long lotteryId){
        return this.selectOne(Condition.create().eq("user_id",userId).eq("lottery_id",lotteryId).eq("visibility",VisibilityEnum.CAN_USE.getCode()));
    }

    public CustomPage<UserLotteryVo> selectSelfPageList(UserLotteryQuery query){
        User user  = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);

        query.setUserId(user.getId());
        return selectPageList(query);
    }

    @Override
    public CustomPage<UserLotteryVo> selectRecyclePageList(UserLotteryQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    public boolean editSelf(UserLotteryQuery query){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        int n = selectCount(Condition.create().eq("id",query.getId()).eq("user_id",user.getId()));
        if(n>0){
            UserLottery userLottery = new UserLottery();
            userLottery.setId(query.getId());
            userLottery.setLoginUser(query.getLoginUser());
            userLottery.setLoginPwd(query.getLoginPwd());
            userLottery.setUpdateUser(user.getUserName());
            if(updateById(userLottery)){
                return true;
            }
        }
        return false;
    }
}
