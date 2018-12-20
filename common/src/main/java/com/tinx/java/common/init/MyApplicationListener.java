package com.tinx.java.common.init;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tinx.java.common.utils.StringUtil;
import com.tinx.java.common.entity.ModelMsg;
import com.tinx.java.common.service.ModelMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * @author tinx
 * @date 2018-10-29 16:56
 */
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("获取所有链接开始!");
        //������������Ϻ��ȡdao�����������ݿ�
       new Thread(new Runnable() {
           @Override
           public void run() {
               ModelMsgService modelMsgService = (ModelMsgService)event.getApplicationContext().getBean("modelMsgServiceImpl");
               //������������Ϻ��ȡ�����ļ��е�����
               RequestMappingHandlerMapping rmhp = event.getApplicationContext().getBean(RequestMappingHandlerMapping.class);
               Map<RequestMappingInfo, HandlerMethod> map = rmhp.getHandlerMethods();
               int i = 0;
               for (RequestMappingInfo info : map.keySet()) {
                   String url = info.getPatternsCondition().toString();
                   url = url.replace("[","").replace("]","");
                   System.out.println(url);
                   String[] arrUrl = url.split("/");
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
               logger.info("��ʼ����ϣ�����ʼ��{}������",i);
           }
       }).start();
    }
}
