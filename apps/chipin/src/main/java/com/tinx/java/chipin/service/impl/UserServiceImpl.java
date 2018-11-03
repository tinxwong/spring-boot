package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.User;
import com.tinx.java.chipin.entity.query.UserQuery;
import com.tinx.java.chipin.entity.vo.UserVo;
import com.tinx.java.chipin.mapper.UserDao1;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public class UserServiceImpl extends ServiceImpl<UserDao1, User> implements UserService {

    @Override
    public CustomPage<UserVo> selectPageList(UserQuery query) {

        PageQuery<User> pagePlus = new PageQuery<User>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        User user = new User();
        BeanUtils.copyProperties(query,user);
        pagePlus.setData(user);

        Page<User> pageList = selectPage(pagePlus.getPagePlus());
        List<User> users = pageList.getRecords();
        List<UserVo> userVos = BeanConverter.copy(users,UserVo.class);
        CustomPage<UserVo> customPage = new CustomPage<UserVo>(pageList);
        customPage.setRows(userVos);
        return customPage;
    }

    @Override
    public Long save(UserQuery query) {

        User user = new User();
        BeanUtils.copyProperties(query,user);
        String pwd = user.getPwd();
        user.setPwd(DigestUtils.md5DigestAsHex(pwd.getBytes()));
        this.insert(user);
        return user.getId();
    }

    @Override
    public boolean editById(UserQuery query) {
        User user = new User();
        BeanUtils.copyProperties(query,user);
        return this.updateById(user);
    }

    @Override
    public boolean revById(Long id) {
        User user = new User();
        user.setId(id);
        user.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(user);
    }

    @Transactional
    public boolean batchRev(List<Long> idList) {
        try{
            for(Long id : idList){
                revById(id);
            }
        }catch (RuntimeException e){
            return false;
        }

        return true;
    }
}
