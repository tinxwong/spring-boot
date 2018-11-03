package com.tinx.java.chipin.core;

import com.tinx.java.chipin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tinx
 * @date 2018-10-7 20:40
 */
@Component
public class LiuLiuLiuAuthent extends DefaultAuthent {

    @Autowired
    private TaskService taskService;

    public TaskService getTaskService(){
        return taskService;
    }

    public String getAuthentName(){
        return "liuLiuLiuAuthent";
    }
}
