package context.contextImpl.highLevel;

import beans.beanDefinition.BeanDefinition;
import com.apple.eawt.ApplicationEvent;
import context.contextImpl.normal.SimpleBeanFactory;
import context.BeanFactory;
import beans.BeansException;
import core.listen.ApplicationEventPublisher;
import core.resource.resourceImpl.ClassPathXmlResource;
import core.resource.Resource;
import core.resource.resourceImpl.XmlBeanDefinitionReader;

/**
 * 高级工厂 Context 实现
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    SimpleBeanFactory simpleBeanFactory;
    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName) throws Exception {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.simpleBeanFactory = beanFactory;
    }

    /**
     * 集成了 BeanFactory 的 getBean 方法
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return this.simpleBeanFactory.getBean(beanName);
    }
    public void registerBeanDefinition(BeanDefinition beanDefinition) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.simpleBeanFactory.registerBeanDefinition(beanDefinition.getId(),beanDefinition);
    }
    @Override
    public boolean containsBean(String name) {
        return this.simpleBeanFactory.containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public void publishEvent(ApplicationEvent event) {

    }
}
