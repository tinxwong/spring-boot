package com.tinx.java.chipin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.tinx.java.chipin.entity.Sysconfig;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.query.TaskQuery;
import com.tinx.java.chipin.entity.vo.TaskVo;
import com.tinx.java.chipin.mapper.TaskDao;
import com.tinx.java.chipin.page.CustomPage;
import com.tinx.java.chipin.page.PageQuery;
import com.tinx.java.chipin.service.SysconfigService;
import com.tinx.java.chipin.service.TaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tinx.java.chipin.utils.FileUtils;
import com.tinx.java.common.response.enums.VisibilityEnum;
import com.tinx.java.common.utils.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 投注任务 服务实现类
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskDao, Task> implements TaskService {

    @Autowired
    private SysconfigService sysconfigService;

    @Override
    public CustomPage<TaskVo> selectPageList(TaskQuery query) {
        PageQuery<Task> pagePlus = new PageQuery<Task>();
        pagePlus.setLimit(query.getLimit());
        pagePlus.setPageIndex(query.getPageIndex());
        Task task = new Task();
        BeanUtils.copyProperties(query,task);
        pagePlus.setData(task);

        Page<Task> pageList = selectPage(pagePlus.getPagePlus());
        List<Task> tasks = pageList.getRecords();
        List<TaskVo> taskVos = BeanConverter.copy(tasks,TaskVo.class);
        CustomPage<TaskVo> customPage = new CustomPage<TaskVo>(pageList);
        customPage.setRows(taskVos);
        return customPage;
    }

    @Override
    public Long save(TaskQuery query) {
        Task task = new Task();
        BeanUtils.copyProperties(query,task);
        this.insert(task);
        query.setId(task.getId());
        initChipinFile(query);
        return task.getId();
    }

    private void initChipinFile(TaskQuery query){
        Sysconfig sysconfig = sysconfigService.getValue("LOTTERY","TEMP");
        String rooPath = sysconfig.getCfgValue();
        String path = rooPath+String.format("/%s/%s/%s/",query.getLotteryId(),query.getId(),query.getUserId());
        String filename = String.format("%s%s%s.txt",query.getLotteryId(),query.getId(),query.getUserId());
        String filePath = path+filename;
        if(!new File(filename).exists()){
            FileUtils.createFile(path,filename);
        }

        if(!StringUtils.isEmpty(query.getChipinContent())){
            FileUtils.writeToFile(filePath,query.getChipinContent());
        }

        Task task = new Task();
        task.setChipinFilePath(filePath);
        task.setId(query.getId());
        updateById(task);
    }

    @Override
    public boolean editById(TaskQuery query) {
        Task task1 = selectById(query.getId());
        Task task = new Task();
        BeanUtils.copyProperties(query,task);
        if(!StringUtils.isEmpty(query.getChipinContent())){
            FileUtils.writeToFile(task1.getChipinFilePath(),query.getChipinContent());
        }
        return this.updateById(task);
    }

    @Override
    public boolean revById(Long id) {

        Task task = new Task();
        task.setId(id);
        task.setVisibility(VisibilityEnum.CAN_NOT_USE.getCode());
        return updateById(task);
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

    public String getFileContent(Long id){
        Task task = selectById(id);
        return FileUtils.readFromFile(task.getChipinFilePath());
    }

}
