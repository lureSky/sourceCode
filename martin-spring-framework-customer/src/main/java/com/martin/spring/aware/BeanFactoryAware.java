package com.martin.spring.aware;

import com.martin.spring.factory.BeanFactory;

/**
 * @author caofeng
 * @date 2020/12/17 15:37
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory);
}
