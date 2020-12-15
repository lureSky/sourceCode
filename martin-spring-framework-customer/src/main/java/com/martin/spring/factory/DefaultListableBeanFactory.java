package com.martin.spring.factory;

import com.martin.spring.definition.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanFactory中集大成的工厂
 * @author caofeng
 * @date 2020/12/15 16:49
 */
public class DefaultListableBeanFactory implements BeanDefinitionRegistry {

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
}
