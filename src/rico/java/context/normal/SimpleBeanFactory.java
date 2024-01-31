package context.normal;

import beans.BeanDefinition;
import beans.BeansException;
import context.interfaces.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程支持的简单 Bean 工厂
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);
    public SimpleBeanFactory() {
    }

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //先尝试直接拿bean实例
        Object singleton = this.getSingleton(beanName);
        //如果此时还没有这个bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            //获取bean的定义
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No bean.");
            }

                singleton = Class.forName(beanDefinition.getClassName()).newInstance();

            //新注册这个bean实例
            this.registerSingleton(beanName, singleton);
        }
        return singleton;
    }
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }
}
