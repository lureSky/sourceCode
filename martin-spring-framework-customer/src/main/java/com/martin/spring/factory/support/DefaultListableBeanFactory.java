package com.martin.spring.factory.support;

import com.martin.spring.definition.BeanDefinition;
import com.martin.spring.factory.AutowiredCapableBeanFactory;
import com.martin.spring.factory.BeanFactory;
import com.martin.spring.factory.ListableBeanFactory;
import com.martin.spring.registry.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeanFactory中集大成的工厂
 * @author caofeng
 * @date 2020/12/15 16:49
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements BeanDefinitionRegistry {

    //内部维护一个BeanDefinition集合
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition bd) {
        beanDefinitions.put(beanName, bd);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.get(beanName);
    }

    @Override
    public Object createBean(String name, BeanDefinition bd) {
        return null;
    }

    @Override
    public Object getBean(String string) {
        return CreateBean(string,beanDefinitions.get(string));
    }


    @Override
    public <T> List<T> getBeansByType(Class<?> type) {
        //根据Bean的类型获取BeanDefinition
        return null;
    }

    @Override
    public List<String> getBeanNamesByType(Class<?> type) {
        return null;
    }
}
