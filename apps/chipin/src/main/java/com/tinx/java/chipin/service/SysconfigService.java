package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.Sysconfig;
import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.query.SysconfigQuery;
import com.tinx.java.chipin.entity.vo.SysconfigVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public interface SysconfigService extends ChipinService<SysconfigQuery,Sysconfig,SysconfigVo> {

    public Sysconfig getValue(String prefix, String suffix);
}
