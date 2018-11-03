package com.tinx.java.chipin.core;

import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.chipin.utils.RandomUtils;
import org.apache.http.client.CookieStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tinx
 * @date 2018-10-7 20:36
 */
@Component
public class JinJiuAuthent extends DefaultAuthent {

    @Autowired
    private TaskService taskService;

    public TaskService getTaskService(){
        return taskService;
    }

    public String getAuthentName(){
        return "jinJiuAuthent";
    }



}
