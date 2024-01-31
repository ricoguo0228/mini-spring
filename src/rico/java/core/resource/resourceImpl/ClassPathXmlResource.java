package core.resource.resourceImpl;

import core.resource.Resource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * 解析 XML 的工作核心类
 */
public class ClassPathXmlResource implements Resource {
    Document document;
    Element rootElement;
    /**
     * 重要部件，用于 XML 中多个资源的迭代
     */
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) throws Exception {
        SAXReader saxReader = new SAXReader();
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        //将配置文件装载进来，生成一个迭代器，可以用于遍历
        this.document = saxReader.read(xmlPath);
        this.rootElement = document.getRootElement();
        this.elementIterator = this.rootElement.elementIterator();
    }

    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    public Object next() {
        return this.elementIterator.next();
    }
}
