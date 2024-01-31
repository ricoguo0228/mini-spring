package context;

import beans.BeanDefinition;
import beans.BeansException;
import context.interfaces.BeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeanFactory 的简单实现
 */
public class SimpleBeanFactory implements BeanFactory {
    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private final List<String> beanNames = new ArrayList<>();
    private final Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {
    }

    /**
     * 容器的核心方法
     */
    public Object getBean(String beanName) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //先尝试直接拿Bean实例
        Object singleton = singletons.get(beanName);
        //如果此时还没有这个Bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            int index = beanNames.indexOf(beanName);
            if (index == -1) {
                throw new BeansException("error");
            } else {
                //获取Bean的定义
                BeanDefinition beanDefinition = beanDefinitions.get(index);
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                //注册Bean实例
                singletons.put(beanDefinition.getId(), singleton);
            }
        }
        return singleton;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
