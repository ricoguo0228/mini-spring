package beans;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException;
    void registerBeanDefinition(BeanDefinition beanDefinition);
}
