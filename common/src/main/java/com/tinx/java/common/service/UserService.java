package com.tinx.java.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.entity.query.UserQuery;
import com.tinx.java.common.entity.vo.UserVo;
import com.tinx.java.common.page.CustomPage;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public interface UserService extends BaseService<UserQuery,User,UserVo>{


    public User doLogin(String username,String pwd);
//
//    public CustomPage<UserVo> selectPageList(UserQuery query);
//
//    public CustomPage<UserVo> selectSelfPageList(UserQuery query);
//
//    public Long save(UserQuery query);
//
//    public boolean editById(UserQuery query);
//
//    public boolean revById(Long id);
//
//    public boolean batchRev(List<Long> idList);
//
//    public boolean editSelf(UserQuery query);
}
