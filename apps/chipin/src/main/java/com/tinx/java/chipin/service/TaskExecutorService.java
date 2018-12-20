package com.tinx.java.chipin.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.chipin.thread.entity.Cathectic;
import com.tinx.java.chipin.thread.entity.ChipinTaskJob;
import com.tinx.java.chipin.thread.entity.K1Cathectic;
import com.tinx.java.chipin.thread.entity.TaskParameter;
import com.tinx.java.chipin.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author tinx
 * @date 2018-9-9 20:54
 */
@Service
public class TaskExecutorService {

    private Logger logger = LoggerFactory.getLogger(TaskExecutorService.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private TaskService taskService;

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private UserLotteryService userLotteryService;

    public void startJob(Long id,Long userId){
        Wrapper wrapper = Condition.create();
        if(id!=null&&id>0){
            wrapper.eq("id",id);
        }
        if(userId!=null&&userId>0){
            wrapper.eq("user_id",userId);
        }
        Task task = taskService.selectOne(wrapper);
        if(task!=null){
            Lottery lottery = lotteryService.selectById(task.getLotteryId());
            UserLottery userLottery = userLotteryService.selectByParam(task.getUserId(),task.getLotteryId());
            ChipinTaskJob chipinTaskJob = new ChipinTaskJob(task,lottery,userLottery);
            MapUtils.put("THREAD_"+id,chipinTaskJob);
            taskExecutor.submit(chipinTaskJob);
        }

    }

    public void stopJob(Long id,Long userId){
        logger.info("停止[THREAD_"+id+"]线程");
        Wrapper wrapper = Condition.create();
        if(id!=null&&id>0){
            wrapper.eq("id",id);
        }
        if(userId!=null&&userId>0){
            wrapper.eq("user_id",userId);
        }
        Task task = taskService.selectOne(wrapper);
        if(task!=null){
            try{
                //停止线程
                ChipinTaskJob chipinTaskJob = (ChipinTaskJob)MapUtils.get("THREAD_"+id);
                chipinTaskJob.setSignal(false);
                //删除cookie
                Lottery lottery = lotteryService.selectById(task.getLotteryId());
                UserLottery userLottery = userLotteryService.selectByParam(task.getUserId(),task.getLotteryId());
                MapUtils.remove(String.format("%s_%s_%s_COOKIE",lottery.getId(),userLottery.getLoginUser(),userLottery.getLoginPwd()));
            }catch (Exception e){
                logger.error(e.getMessage());
            }
            updateTaskStatus(id,TaskStatusEnum.STOP.getCode());
        }


    }


    public void updateTaskStatus(Long id,int status){
        Task task = new Task();
        task.setId(id);
        task.setStatus(status);
        taskService.updateById(task);
    }
}
