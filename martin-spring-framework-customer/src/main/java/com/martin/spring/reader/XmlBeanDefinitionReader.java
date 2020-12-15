package com.martin.spring.reader;

import com.martin.spring.factory.BeanDefinitionRegistry;
import com.martin.spring.factory.DefaultListableBeanFactory;
import com.martin.spring.resource.Resource;
import com.martin.spring.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author caofeng
 * @date 2020/12/15 16:54
 */
public class XmlBeanDefinitionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = resource.getResourceAsStream();
        Document document = DocumentReader.createDocument(inputStream);
        XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        documentReader.loadBeanDefinitions(document.getRootElement());
    }
}
