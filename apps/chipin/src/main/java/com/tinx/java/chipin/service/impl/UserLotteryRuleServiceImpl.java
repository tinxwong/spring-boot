package com.tinx.java.chipin.service.impl;

import com.tinx.java.chipin.entity.UserLotteryRule;
import com.tinx.java.chipin.entity.query.UserLotteryRuleQuery;
import com.tinx.java.chipin.entity.vo.UserLotteryRuleVo;
import com.tinx.java.chipin.mapper.UserLotteryRuleDao;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.service.UserLotteryRuleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-10-07
 */
@Service
public class UserLotteryRuleServiceImpl extends ServiceImpl<UserLotteryRuleDao, UserLotteryRule> implements UserLotteryRuleService {

    @Override
    public CustomPage<UserLotteryRuleVo> selectPageList(UserLotteryRuleQuery query) {
        return null;
    }

    @Override
    public Long save(UserLotteryRuleQuery query) {
        return null;
    }

    @Override
    public boolean editById(UserLotteryRuleQuery query) {
        return false;
    }

    @Override
    public boolean revById(Long id) {
        return false;
    }

    @Override
    public boolean batchRev(List<Long> idList) {
        return false;
    }
}
