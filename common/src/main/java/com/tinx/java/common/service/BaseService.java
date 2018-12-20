package com.tinx.java.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.common.page.CustomPage;

import java.util.List;

/**
 * @author tinx
 * @date 2018-11-12 22:49
 */
public interface BaseService<Q,T,V> extends IService<T> {

    public CustomPage<V> selectPageList(Q query);

    public Long save(Q query);

    public boolean editById(Q query);

    public boolean editSelf(Q query);

    public boolean revById(Long id);

    public boolean batchRev(List<Long> idList);

    public CustomPage<V> selectSelfPageList(Q query);

    public CustomPage<V> selectRecyclePageList(Q query);

    public boolean restore(Long id);
}
