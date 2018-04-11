package cn.bysj.core.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/** 
* 
* @author victor.qin 
* @date 2018/3/27 14:31
*/ 
@Component
public class AppListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        LOGGER.info("<=====论文双选系统启动成功=====>");
    }

}
