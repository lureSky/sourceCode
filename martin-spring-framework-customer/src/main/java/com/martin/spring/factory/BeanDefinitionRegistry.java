package com.martin.spring.factory;

import com.martin.spring.definition.BeanDefinition;

/**
 * 负责对BeanDefinition的管理和注册工作
 * @author caofeng
 * @date 2020/12/15 16:59
 */
public interface BeanDefinitionRegistry {

    /**
     * 建立beanName和BeanDefinition的映射关系
     * @param beanName
     * @param bd
     */
    void registerBeanDefinition(String beanName, BeanDefinition bd);

    BeanDefinition getBeanDefinition(String beanName);
}
