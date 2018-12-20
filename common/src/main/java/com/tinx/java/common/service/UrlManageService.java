package com.tinx.java.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.common.entity.UrlManage;
import com.tinx.java.common.entity.query.UrlManageQuery;
import com.tinx.java.common.entity.vo.FirstLevel;
import com.tinx.java.common.entity.vo.UrlManageVo;

import java.util.List;

/**
 * @author tinx
 * @date 2018-11-7 20:48
 */
public interface UrlManageService extends BaseService<UrlManageQuery,UrlManage,UrlManageVo> {

    public List<UrlManage> loadTabUrlManage();

    public List<UrlManage> loadUrlManagesByRootId(Long rootId);

    public List<UrlManage> loadButtonUrlManage(String appName,String moduleName);

    public List<UrlManage> loadUrlUrlManage(String appName,String moduleName);


}