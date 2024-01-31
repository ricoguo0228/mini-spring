package core.listen;

import com.apple.eawt.ApplicationEvent;

/**
 * 监听事件发布者
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
