package com.tinx.java.permission.service;

import com.tinx.java.permission.entity.Button;
import com.tinx.java.permission.entity.Url;
import com.tinx.java.permission.entity.vo.FirstLevel;

import java.util.List;
import java.util.Map;

/**
 * @author tinx
 * @date 2018-11-8 18:16
 */
public interface PermissionService {

    public List<Map<String,String>> getTab();

    public FirstLevel getPageMenu(Long rootId);

    public String test();

    public Map<String ,Object> getOwnUrl();

    public List<Button> loadButton(String appName, String moduleName);

    public List<Url> loadUrl(String appName, String moduleName);

    public int checkUserRight(String url);
}
