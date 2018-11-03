package com.tinx.java.chipin.service;

import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.query.SysconfigQuery;
import com.tinx.java.chipin.page.CustomPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author tinx
 * @date 2018-8-31 21:19
 */
public interface ChipinService<Q,T,V> extends IService<T> {

    public CustomPage<V> selectPageList(Q query);

    public Long save(Q query);

    public boolean editById(Q query);

    public boolean revById(Long id);

    public boolean batchRev(List<Long> idList);
}
