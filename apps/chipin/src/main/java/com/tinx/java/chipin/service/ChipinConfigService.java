package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.ChipinConfig;
import com.tinx.java.chipin.entity.query.ChipinConfigQuery;
import com.tinx.java.chipin.entity.vo.ChipinConfigVo;
import com.tinx.java.common.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public interface ChipinConfigService extends BaseService<ChipinConfigQuery,ChipinConfig,ChipinConfigVo> {

    public ChipinConfig getValue(String prefix, String suffix);
}
