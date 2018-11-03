package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.query.UserQuery;
import com.tinx.java.chipin.entity.vo.UserVo;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public interface UserService extends ChipinService<UserQuery,User,UserVo> {

}
