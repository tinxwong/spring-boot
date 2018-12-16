package com.tinx.java.common.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.entity.query.UserQuery;
import com.tinx.java.common.entity.vo.UserVo;
import com.tinx.java.common.mapper.UserDao;
import com.tinx.java.common.page.CustomPage;
import com.tinx.java.common.page.PageQuery;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.service.UserService;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.common.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public CustomPage<UserVo> selectPageList(UserQuery query) {

        PageQuery<User> pagePlus = new PageQuery<User>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Wrapper wrapper = Condition.create();
        if(query.getVisibility()==null){
            query.setVisibility(1);
        }
        wrapper.eq("visibility",query.getVisibility());
        if(query.getId()!=null){
            wrapper.eq("id",query.getId());
        }

        if(StringUtils.isNotEmpty(query.getUserName())){
            wrapper.eq("user_name",query.getUserName());
        }

        Page<User> pageList = selectPage(pagePlus.getPagePlus(),wrapper);
        List<User> users = pageList.getRecords();
        List<UserVo> userVos = BeanConverter.copy(users,UserVo.class);
        CustomPage<UserVo> customPage = new CustomPage<UserVo>(pageList);
        customPage.setRows(userVos);
        return customPage;
    }

    public CustomPage<UserVo> selectSelfPageList(UserQuery query){
        User user  = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        query.setId(user.getId());
        return selectPageList(query);
    }

    @Override
    public CustomPage<UserVo> selectRecyclePageList(UserQuery query) {
        query.setVisibility(0);
        return selectPageList(query);
    }

    @Override
    public boolean restore(Long id) {
        User user = new User();
        user.setId(id);
        user.setVisibility(VisibilityEnum.CAN_USE.getCode());
        return updateById(user);
    }

    @Override
    public Long save(UserQuery query) {
        User loginUser = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        User user = new User();
        BeanUtils.copyProperties(query,user);
        String pwd = user.getPwd();
        user.setPwd(DigestUtils.md5DigestAsHex(pwd.getBytes()));
        user.setVisibility(VisibilityEnum.CAN_USE.getCode());
        user.setCreateUser(loginUser.getUserName());
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

    public User doLogin(String username,String pwd){
        return selectOne(Condition.create().eq("user_name",username).eq("pwd", StringUtil.MD5(pwd)).eq("visibility","1"));
    }

    public boolean editSelf(UserQuery query){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        User updateUser = new User();
        if(query.getId()==user.getId()){
            updateUser.setId(query.getId());
            updateUser.setPwd(StringUtil.MD5(query.getPwd()));
            updateUser.setPhone(query.getPhone());
            updateUser.setCreateUser(user.getUserName());
        }
        return updateById(updateUser);
    }
}
