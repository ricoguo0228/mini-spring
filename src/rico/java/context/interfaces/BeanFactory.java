package context.interfaces;

import beans.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException;
    Boolean containsBean(String name);
    void registerBean(String beanName, Object obj);
}
