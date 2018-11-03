package com.tinx.java.permission.init;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.utils.StringUtil;
import com.tinx.java.permission.entity.ModelMsg;
import com.tinx.java.permission.service.ModelMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author tinx
 * @date 2018-10-29 16:56
 */
@Service
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("系统初始化完毕，遍历应用链接开始!");
        //在容器加载完毕后获取dao层来操作数据库
       new Thread(new Runnable() {
           @Override
           public void run() {
               ModelMsgService modelMsgService = (ModelMsgService)event.getApplicationContext().getBean("modelMsgServiceImpl");
               //在容器加载完毕后获取配置文件中的配置
               RequestMappingHandlerMapping rmhp = event.getApplicationContext().getBean(RequestMappingHandlerMapping.class);
               Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
               int i = 0;
               for (RequestMappingInfo info : map.keySet()) {
                   String url = info.getPatternsCondition().toString();
                   url = url.replace("[","").replace("]","");
                   System.out.println(url);
                   String[] arrUrl = url.split("/");
                   if(arrUrl.length>3){
                       String mdKey = StringUtil.MD5(url);
                       try{
                           ModelMsg msg = modelMsgService.selectOne(Condition.create().eq("md_key",mdKey));
                           if(msg!=null){
                                continue;
                           }
                           ModelMsg modelMsg = new ModelMsg();
                           modelMsg.setAppName(arrUrl[1]);
                           modelMsg.setModuleName(arrUrl[2]);
                           modelMsg.setMdKey(mdKey);
                           modelMsg.setUrl(url);
                           modelMsgService.insert(modelMsg);
                       }catch (RuntimeException e){
                           logger.error(e.getMessage());
                       }
                       i++;
                   }

               }
               logger.info("初始化完毕，共初始化{}条数据",i);
           }
       }).start();
    }
}
