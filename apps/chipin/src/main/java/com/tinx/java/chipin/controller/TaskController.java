package com.tinx.java.chipin.controller;


import com.tinx.java.chipin.entity.query.TaskQuery;
import com.tinx.java.chipin.entity.vo.TaskVo;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.TaskExecutorService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 投注任务 前端控制器
 * </p>
 *
 * @author tinx123
 * @since 2018-08-11
 */
@Controller
@RequestMapping("/chipin/task")
public class TaskController extends ChipInController<TaskQuery,TaskVo>{

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskExecutorService taskExecutorService;

    @Override
    public String getModuleName() {
        return "task";
    }

    public ChipinService getServiceName(){
        return taskService;
    }

    @RequestMapping(value = "/startJob",method = RequestMethod.POST)
    @ResponseBody
    public boolean startJob(@RequestParam("id")Long id){
        taskExecutorService.startJob(id);
        return true;
    }

    @RequestMapping(value = "/stopJob",method = RequestMethod.POST)
    @ResponseBody
        public boolean stopJob(@RequestParam("id")Long id){
        taskExecutorService.stopJob(id);
        return true;
    }

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse del(@RequestParam("id")Long id){
        return ResultUtil.makeBaseResponse(taskService.deleteById(id), ResponseCode.SUCCESS);
    }


    @RequestMapping(value = "/getFileContent",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getFileContent(@RequestParam("id")Long id){
        return ResultUtil.makeBaseResponse(taskService.getFileContent(id),ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/editById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseResponse editById(TaskQuery query){
        if(getServiceName().editById(query)){
            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
        }else{
            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
        }
    }
}

