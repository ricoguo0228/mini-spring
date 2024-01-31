package beans.beanDefinition;

import beans.BeansException;

/**
 * BeanDefinition 的集中区域
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String name, BeanDefinition bd) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException;
    void removeBeanDefinition(String name);
    BeanDefinition getBeanDefinition(String name);
    boolean containsBeanDefinition(String name);
}
