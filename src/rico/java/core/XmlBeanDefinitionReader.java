package core;

import beans.BeanDefinition;
import context.interfaces.BeanFactory;
import context.normal.SimpleBeanFactory;
import core.interfaces.Resource;
import org.dom4j.Element;

/**
 * 将 XML 解析出来的内容转换为 BeanDefinition
 */
public class XmlBeanDefinitionReader {
    SimpleBeanFactory simpleBeanFactory;
    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
        this.simpleBeanFactory = beanFactory;
    }
    public void loadBeanDefinitions(Resource resource) {
        // 一次性把资源全加载完
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
