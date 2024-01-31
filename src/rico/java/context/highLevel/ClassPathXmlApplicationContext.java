package context;

import beans.BeanDefinition;
import context.interfaces.BeanFactory;
import beans.BeansException;
import core.ClassPathXmlResource;
import core.interfaces.Resource;
import core.XmlBeanDefinitionReader;

/**
 * 高级工厂 Context 实现
 */
public class ClassPathXmlApplicationContext implements BeanFactory{
    BeanFactory beanFactory;
    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName) throws Exception {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    /**
     * 集成了 BeanFactory 的 getBean 方法
     * @param beanName
     * @return
     * @throws BeansException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Object getBean(String beanName) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return this.beanFactory.getBean(beanName);
    }
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
}
