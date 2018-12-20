package com.tinx.java.common.service;

import com.tinx.java.common.entity.ModelMsg;
import com.tinx.java.common.entity.UrlManage;
import com.tinx.java.common.entity.query.ModelMsgQuery;
import com.tinx.java.common.entity.vo.ModelMsgVo;

import java.util.List;

/**
 * <p>
 *  ������
 * </p>
 *
 * @author tinx123
 * @since 2018-10-29
 */
public interface ModelMsgService extends BaseService<ModelMsgQuery,ModelMsg,ModelMsgVo> {

    public List<String> selectDistinctAppName();

    public List<String> selectDistinctModuleNameByAppName(String appName);

    public List<ModelMsgVo> selectList(String appName, String moduleName);

}
