package core.resource.resourceImpl;

import beans.BeansException;
import beans.beanDefinition.BeanDefinition;
import context.contextImpl.normal.SimpleBeanFactory;
import core.resource.Resource;
import org.dom4j.Element;

/**
 * 将 XML 解析出来的内容转换为 BeanDefinition
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;
    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
        this.simpleBeanFactory = beanFactory;
    }
    public void loadBeanDefinitions(Resource resource) throws BeansException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 一次性把资源全加载完
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.simpleBeanFactory.registerBeanDefinition(beanID,beanDefinition);
        }
    }
}
