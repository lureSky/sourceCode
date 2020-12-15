package com.martin.spring.test;

import com.martin.spring.factory.BeanDefinitionRegistry;
import com.martin.spring.factory.DefaultListableBeanFactory;
import com.martin.spring.reader.XmlBeanDefinitionReader;
import com.martin.spring.resource.ClasspathResource;
import com.martin.spring.resource.Resource;
import org.junit.Test;

/**
 * @author caofeng
 * @date 2020/12/15 16:39
 */
public class TestSpringFramework {
    @Test
    public void test() throws Exception {
        //1.指定spring配置文件路径
        String location="beans.xml";
        // 将路径封装成资源对象
        Resource resource = new ClasspathResource(location);
        //2.创建BeanDefinitionRegistry
        BeanDefinitionRegistry beanDefinitionRegistry = new DefaultListableBeanFactory();
        //3.加载spring配置文件，并最终将解析出来的BeanDefinition注册到BeanDefinitionRegistry
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanDefinitionRegistry);
        reader.loadBeanDefinitions(resource);
        System.out.println("");
    }
}
