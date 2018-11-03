package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.Lottery;
import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.query.LotteryQuery;
import com.tinx.java.chipin.entity.vo.LotteryVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public interface LotteryService extends ChipinService<LotteryQuery,Lottery,LotteryVo> {

}
