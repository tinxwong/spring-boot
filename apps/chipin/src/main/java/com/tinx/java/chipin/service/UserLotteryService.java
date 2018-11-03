package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.entity.query.UserLotteryQuery;
import com.tinx.java.chipin.entity.vo.UserLotteryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-09-01
 */
public interface UserLotteryService extends ChipinService<UserLotteryQuery,UserLottery,UserLotteryVo> {

    public UserLottery selectByUserId(Long userId);
}
