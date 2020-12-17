package com.martin.spring.factory.support;

import com.martin.spring.definition.BeanDefinition;
import com.martin.spring.factory.ListableBeanFactory;
import com.martin.spring.registry.DefaultSingletonBeanRegistry;

/**
 * 不要左右的办法都交给DefaultListableBeanFactory
 * 相当于一个 缓冲类
 * <p>
 * 抽象模板设计模式中一个抽象类
 * 一般有父类和子类（抽取公共行为）
 * 父类制定统一的步骤
 * 子类负责实现特定的步骤
 *
 * @author caofeng
 * @date 2020/12/17 10:17
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ListableBeanFactory {

    //先写好固定流程，然后写一个抽象方法给儿子去实现
    @Override
    public Object getBean(String name) {
        //1.获取缓存中的单例bean的实例
        Object singleton = getSingleton(name);
        //2.如果有结果，则直接返回
        if (singleton != null) {
            return singleton;
        }
        //3.如果没有结果，则获取指定名称的BeanDefinition对象
        BeanDefinition bd = getBeanDefinition(name);
        //如果bd为null，则直接返回
        if (bd == null) {
            return null;
        }
        //  通过BeanDefinition中的scope信息，判断是单例还是多例，如果是单例则加入单例bean缓存
        if (bd.isSingleton()) {
            //4.1创建Bean实例，根据BeanDefinition（子类实现）
            singleton = createBean(name,bd);
            //4.2放入单例Bean缓存
            registerSingleton(name,singleton);
        } else if (bd.isPrototype()) {
            //5.如果是多例bean
            //5.1根据BeanDefinition创建Bean实例，并返回
            Object prototype = createBean(name,bd);
            return prototype;
        } else {
            return null;
        }
        return null;
    }

    /**
     * 让子类实现
     *
     * @param name
     * @return
     */
    public abstract BeanDefinition getBeanDefinition(String name);


    /**
     * 创建bean因为在autowiredCapableBeanFactory中，因此，需要再简历一个中间对象
     * @param name
     * @param bd
     * @return
     */
    public abstract Object createBean(String name,BeanDefinition bd);
}
