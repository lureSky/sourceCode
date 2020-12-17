package com.martin.spring.factory;

import com.martin.spring.definition.BeanDefinition;

/**
 * 设计原则：    beanFactory属于顶层，下面的ListableBeanFactory和AutowiredCapableBeanFactory属于子接口利于扩展
 * 主要作用：    具有创建Bean的功能，创建Bean需要对Bean进行装配
 * @author caofeng
 * @date 2020/12/17 10:12
 */
public interface AutowiredCapableBeanFactory extends BeanFactory{

    /**
     * @Description: 创建Bean实例
     * @param name
     * @param bd
     * @return: java.lang.Object
     * @author: Caofeng
     * @mail: 867403822@qq.com
     * @date: 2020-12-17 10:16
    */
    Object CreateBean(String name, BeanDefinition bd);
}
