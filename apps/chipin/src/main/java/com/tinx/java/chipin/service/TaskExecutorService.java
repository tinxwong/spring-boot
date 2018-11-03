package com.tinx.java.chipin.service;

import com.tinx.java.chipin.entity.Lottery;
import com.tinx.java.chipin.entity.Task;
import com.tinx.java.chipin.entity.UserLottery;
import com.tinx.java.chipin.enums.TaskStatusEnum;
import com.tinx.java.chipin.thread.entity.Cathectic;
import com.tinx.java.chipin.thread.entity.ChipinTaskJob;
import com.tinx.java.chipin.thread.entity.K1Cathectic;
import com.tinx.java.chipin.thread.entity.TaskParameter;
import com.tinx.java.chipin.utils.MapUtils;
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

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private TaskService taskService;

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private UserLotteryService userLotteryService;

    public void startJob(Long id){
        Task task = taskService.selectById(id);
        Lottery lottery = lotteryService.selectById(task.getLotteryId());
        UserLottery userLottery = userLotteryService.selectByUserId(task.getUserId());
//        Cathectic cathectic = new K1Cathectic(lottery);
        ChipinTaskJob chipinTaskJob = new ChipinTaskJob(task,lottery,userLottery);
        MapUtils.put("THREAD_"+id,chipinTaskJob);
        taskExecutor.submit(chipinTaskJob);
    }

    public void stopJob(Long id){
        System.out.println("停止[THREAD_"+id+"]线程");
        ChipinTaskJob chipinTaskJob = (ChipinTaskJob)MapUtils.get("THREAD_"+id);
        chipinTaskJob.setSignal(false);
        updateTaskStatus(id,TaskStatusEnum.STOP.getCode());

    }


    public void updateTaskStatus(Long id,int status){
        Task task = new Task();
        task.setId(id);
        task.setStatus(status);
        taskService.updateById(task);
    }
}
