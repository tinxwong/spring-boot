package com.tinx.java.permission.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.entity.UrlManage;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.enums.UrlTypeEnum;
import com.tinx.java.common.exception.IllegalException;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.UrlManageService;
import com.tinx.java.common.utils.BeanConverter;
import com.tinx.java.common.utils.StringUtil;
import com.tinx.java.permission.entity.Button;
import com.tinx.java.permission.entity.Url;
import com.tinx.java.permission.entity.vo.FirstLevel;
import com.tinx.java.permission.entity.vo.SecondLevel;
import com.tinx.java.permission.entity.vo.ThridLevel;
import com.tinx.java.common.utils.ServletUtils;
import com.tinx.java.permission.entity.ObjPermission;
import com.tinx.java.permission.service.ObjPermissionService;
import com.tinx.java.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author tinx
 * @date 2018-11-8 18:16
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private ObjPermissionService objPermissionService;

    @Autowired
    private UrlManageService urlManageService;

    public List<Map<String,String>> getTab(){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        List<Map<String,String>> tabs = new ArrayList<>();


        List<UrlManage> urlManages = urlManageService.loadTabUrlManage();
        if(user.getRoleId()!=1){//���ǳ��ܣ�ȡ���ܿ�����Ŀ
            List<String> permissionMD5 = loadUserPermissionsMD5();
            if(CollectionUtils.isEmpty(permissionMD5)){
                throw new RuntimeException("�ý�ɫ��ʱû��Ȩ�ޣ�");
            }
            urlManages = loadOwnerUrlManage(urlManages,permissionMD5);
        }

        for(UrlManage urlManage:urlManages){
            Map<String,String> map = new HashMap<>();
            map.put("id",""+urlManage.getId());
            map.put("text",urlManage.getText());
            tabs.add(map);
        }
        objPermissionService.selectList(Condition.create().eq("obj_id",user.getRoleId()).eq("obj_type","ROLE"));
        return tabs;

    }

    private List<UrlManage> loadOwnerUrlManage(List<UrlManage> urlManages,List<String> permissionMD5s){
        List<UrlManage> ownerUrlManage = new ArrayList<>();
        for(UrlManage urlManage:urlManages){
            if(permissionMD5s.contains(urlManage.getMdUrl())){
                ownerUrlManage.add(urlManage);
            }
        }
        return ownerUrlManage;
    }

    public FirstLevel getPageMenu(Long rootId){
        HttpSession session = ServletUtils.getSession();
        System.out.println("session.getId():"+session.getId());
        FirstLevel firstLevel = getChannelStruct(rootId);
//        FirstLevel firstLevel = (FirstLevel)session.getAttribute(Helper.SESSION_USER_PERMISSION_MENU+"_"+rootId);
//
//        if(firstLevel == null){
//            firstLevel = getChannelStruct(rootId);
//            ServletUtils.getSession().setAttribute(Helper.SESSION_USER_PERMISSION_MENU+"_"+rootId,firstLevel);
//        }
        return firstLevel;
    }


    public Map<String ,Object> getOwnUrl(){
        Map<String ,Object> allMenu = new HashMap<String,Object>();
        List<Map<String,String>> tabs = getTab();
        List<FirstLevel> firstLevels = new ArrayList<>();
        for(Map<String,String> map : tabs){
            long id = Long.parseLong(map.get("id"));
            FirstLevel firstLevel = getPageMenu(id);
            firstLevels.add(firstLevel);
        }
        allMenu.put("tab",tabs);
        allMenu.put("menu",firstLevels);
        return allMenu;
    }


    private List<String> extractMDUrl(List<ObjPermission> objPermissions){
        List<String> mdUrls = new ArrayList<>();
        for(ObjPermission objPermission:objPermissions){
            mdUrls.add(objPermission.getMdKey());
        }

       return mdUrls;
    }

    /**
     * �û���Ȩ��key
     * @return
     */
    private List<String> loadUserPermissionsMD5(){
        HttpSession session = ServletUtils.getSession();
        System.out.println("loadUserPermissionsMD5:"+session.getId());
        List<String> permissionMD5 = (List<String>)ServletUtils.getSession().getAttribute(Helper.SESSION_USER_PERMISSION_MD5);
        if(CollectionUtils.isEmpty(permissionMD5)){
            User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
            List<ObjPermission> objPermissions = objPermissionService.selectList(Condition.create().eq("obj_id",user.getRoleId()).eq("obj_type","ROLE"));
            ServletUtils.getSession().setAttribute(Helper.SESSION_USER_PERMISSION_SET,objPermissions);
            permissionMD5 = extractMDUrl(objPermissions);
            ServletUtils.getSession().setAttribute(Helper.SESSION_USER_PERMISSION_MD5,permissionMD5);
        }
        return permissionMD5;
    }

    /**
     * ����Ŀת��ǰ������Ҫ��json��ʽ����״�ṹ
     * @param rootId
     * @return
     */
    private FirstLevel getChannelStruct(Long rootId){
        List<UrlManage> urlManages = (List<UrlManage>)ServletUtils.getSession().getAttribute(String.format("%s_%s",Helper.SESSION_USER_PERMISSION_URL_MANAGE,rootId));
        if(CollectionUtils.isEmpty(urlManages)){
            urlManages = urlManageService.loadUrlManagesByRootId(rootId);
        }
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        if(user.getRoleId()!=1){//����û����ǳ�������Ա������Ҫȡ����Ӧ����Ŀ
            List<String> permissionMD5s = loadUserPermissionsMD5();
            urlManages = loadOwnerUrlManage(urlManages,permissionMD5s);
        }
        FirstLevel firstLevel = new FirstLevel();
        firstLevel.setId(rootId);
        SecondLevel secondLevel = new SecondLevel();
        List<SecondLevel> list = new ArrayList<>();
        List<ThridLevel> umList = new ArrayList<>();
        for(UrlManage um : urlManages){
            if(um.getParentId()==0){
                secondLevel.setText(um.getText());
                list.add(secondLevel);
            }else{
                ThridLevel thridLevel = new ThridLevel();
                thridLevel.setId(um.getId());
                thridLevel.setText(um.getText());
                thridLevel.setHref(um.getHref());
                umList.add(thridLevel);
            }
        }
        secondLevel.setItems(umList);
        firstLevel.setMenu(list);
        return firstLevel;
    }



    public String test(){
        try{
            int n = 2/0;
        }catch (RuntimeException e){
            throw new IllegalException(ResponseCode.PARAMS_INVALID);
        }
        return "";
    }


    public List<Button> loadButton(String appName,String moduleName){
        List<ObjPermission> objPermissions = new ArrayList<>();
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        List<ObjPermission> rolePermissions = objPermissionService.selectList(Condition.create().eq("app_name",appName).eq("module_name",moduleName).eq("obj_type","ROLE").eq("obj_id",user.getRoleId()).eq("url_type", UrlTypeEnum.BUTTON.getCode()));
        List<ObjPermission> personalPermissions = objPermissionService.selectList(Condition.create().eq("app_name",appName).eq("module_name",moduleName).eq("obj_type","PERSONAL").eq("obj_id",user.getId()).eq("url_type", UrlTypeEnum.BUTTON.getCode()));
        objPermissions.addAll(rolePermissions);
        objPermissions.addAll(personalPermissions);
        return BeanConverter.copy(objPermissions, Button.class);
    }

    public List<Url> loadUrl(String appName, String moduleName){
        List<ObjPermission> objPermissions = new ArrayList<>();
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        List<ObjPermission> rolePermissions = objPermissionService.selectList(Condition.create().eq("app_name",appName).eq("module_name",moduleName).eq("obj_type","ROLE").eq("obj_id",user.getRoleId()).eq("url_type", UrlTypeEnum.URL.getCode()));
        List<ObjPermission> personalPermissions = objPermissionService.selectList(Condition.create().eq("app_name",appName).eq("module_name",moduleName).eq("obj_type","PERSONAL").eq("obj_id",user.getId()).eq("url_type", UrlTypeEnum.URL.getCode()));
        objPermissions.addAll(rolePermissions);
        objPermissions.addAll(personalPermissions);
        return BeanConverter.copy(objPermissions,Url.class);
    }

    public int checkUserRight(String url){
        User user = (User)ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        int n = 0;
        String mdKey = StringUtil.MD5(url);
        n = objPermissionService.selectCount(Condition.create().eq("md_key",mdKey).eq("visibility", VisibilityEnum.CAN_USE.getCode()).eq("obj_id",user.getRoleId()).eq("obj_type","ROLE"));
        if(n>0){
            return n;
        }else{
            n = objPermissionService.selectCount(Condition.create().eq("md_key",mdKey).eq("visibility", VisibilityEnum.CAN_USE.getCode()).eq("obj_id",user.getId()).eq("obj_type","PERSONAL"));
        }
        return n;
    }

}
