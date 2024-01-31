package context.contextImpl.normal;

import beans.beanDefinition.BeanDefinition;
import beans.BeansException;
import beans.beanDefinition.BeanDefinitionRegistry;
import context.BeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程支持的简单 Bean 工厂
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            getBean(name);
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    @Override
    public Object getBean(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (containsBean(name)) {
            if (this.containsSingleton(name)) {
                return this.getSingleton(name);
            } else {
                BeanDefinition beanDefinition = this.getBeanDefinition(name);
                Object singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                this.registerSingleton(name, singleton);
                return singleton;
            }
        } else {
            return null;
        }

    }

    @Override
    public boolean containsBean(String name) {
        return this.beanDefinitionNames.contains(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }
}
