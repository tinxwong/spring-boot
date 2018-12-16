package com.tinx.java.chipin.controller;


import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.RuleBean;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.query.TaskQuery;
import com.tinx.java.chipin.entity.vo.TaskVo;
import com.tinx.java.chipin.service.AssignService;
import com.tinx.java.chipin.service.ChipinService;
import com.tinx.java.chipin.service.TaskExecutorService;
import com.tinx.java.chipin.service.TaskService;
import com.tinx.java.common.constraint.Helper;
import com.tinx.java.common.constraint.UrlType;
import com.tinx.java.common.controller.BaseController;
import com.tinx.java.common.entity.User;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.service.BaseService;
import com.tinx.java.common.utils.ResultUtil;
import com.tinx.java.common.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
public class TaskController extends BaseController<TaskQuery,TaskVo> {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskExecutorService taskExecutorService;

    @Autowired
    private AssignService assignService;

    @Override
    public String getModuleName() {
        return "task";
    }

    public BaseService getServiceName(){
        return taskService;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET,name= UrlType.CHANNEL)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<RuleBean> rules = assignService.getRule();
        List<User> users = assignService.getUsers();
        List<Lottery> lotteries = assignService.getLotterys();
        modelAndView.addObject("users",users);
        modelAndView.addObject("rules",rules);
        modelAndView.addObject("lotteries",lotteries);
        modelAndView.setViewName(getModuleName()+"/index");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET,name=UrlType.BUTTON)
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView();
        List<RuleBean> rules = assignService.getRule();
        List<User> users = assignService.getUsers();
        List<Lottery> lotteries = assignService.getLotterys();
        modelAndView.addObject("users",users);
        modelAndView.addObject("rules",rules);
        modelAndView.addObject("lotteries",lotteries);
        modelAndView.setViewName(getModuleName()+"/add");
        return modelAndView;
    }

    @RequestMapping(value = "/startJob",method = RequestMethod.POST,name= UrlType.URL)
    @ResponseBody
    public boolean startJob(@RequestParam("id")Long id){
        taskExecutorService.startJob(id,null);
        return true;
    }

    @RequestMapping(value = "/stopJob",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
        public boolean stopJob(@RequestParam("id")Long id){
        taskExecutorService.stopJob(id,null);
        return true;
    }

    @RequestMapping(value = "/startMyJob",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
    public boolean startMyJob(@RequestParam("id")Long id){
        User user = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        taskExecutorService.startJob(id,user.getId());
        return true;
    }

    @RequestMapping(value = "/stopMyJob",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
    public boolean stopMyJob(@RequestParam("id")Long id){
        User user = (User) ServletUtils.getSession().getAttribute(Helper.SESSION_USER);
        taskExecutorService.stopJob(id,user.getId());
        return true;
    }

//    @RequestMapping(value = "/del",method = RequestMethod.POST,name=UrlType.URL)
//    @ResponseBody
//    public BaseResponse del(@RequestParam("id")Long id){
//        return ResultUtil.makeBaseResponse(taskService.deleteById(id), ResponseCode.SUCCESS);
//    }


    @RequestMapping(value = "/getFileContent",method = RequestMethod.POST,name=UrlType.URL)
    @ResponseBody
    public BaseResponse getFileContent(@RequestParam("id")Long id){
        return ResultUtil.makeBaseResponse(taskService.getFileContent(id),ResponseCode.SUCCESS);
    }

//    @RequestMapping(value = "/editById", method = {RequestMethod.GET, RequestMethod.POST},name=UrlType.URL)
//    @ResponseBody
//    public BaseResponse editById(TaskQuery query){
//        if(getServiceName().editById(query)){
//            return ResultUtil.makeBaseResponse(Boolean.TRUE, ResponseCode.SUCCESS);
//        }else{
//            return ResultUtil.makeBaseResponse(Boolean.FALSE, ResponseCode.ERROR);
//        }
//    }
}

