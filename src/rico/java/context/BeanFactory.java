package context;

import beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException;
    boolean containsBean(String name);
    boolean isSingleton(String name);
    boolean isPrototype(String name);
    Class<?> getType(String name);
}
