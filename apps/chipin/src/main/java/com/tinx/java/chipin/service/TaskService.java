package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.Task;
import com.baomidou.mybatisplus.service.IService;
import com.tinx.java.chipin.entity.query.TaskQuery;
import com.tinx.java.chipin.entity.vo.TaskVo;
import org.apache.tomcat.util.threads.TaskQueue;

/**
 * <p>
 * 投注任务 服务类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
public interface TaskService extends ChipinService<TaskQuery,Task,TaskVo> {

    public String getFileContent(Long id);
}
