package cn.iocoder.yudao.framework.mq.core.bus;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 基于 Spring Cloud Bus 实现的 Producer 抽象类
 *
 * @author 芋道源码
 */
public abstract class AbstractBusProducer {

    @Resource
    protected ApplicationEventPublisher applicationEventPublisher;

    @Resource
    protected ServiceMatcher serviceMatcher;

    @Value("${spring.application.name}")
    protected String applicationName;

    protected void publishEvent(RemoteApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    /**
     * @return 只广播给自己服务的实例
     */
    protected String selfDestinationService() {
        return applicationName + ":**";
    }

    protected String getBusId() {
        return serviceMatcher.getBusId();
    }

}
