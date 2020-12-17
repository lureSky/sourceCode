package com.martin.spring.factory;

import java.util.List;

/**
 * 具有列表化功能的bean工厂
 * 可以以列表的形式展示容器中的bean实例获取其他
 * @author caofeng
 * @date 2020/12/17 10:05
 */
public interface ListableBeanFactory extends BeanFactory{


    /**
     * @Description: 根据Bean的类型获取Bean的实例（集合）
     * @param type  bean类型
     * @return: java.util.List<T>
     * @author: Caofeng
     * @mail: 867403822@qq.com
     * @date: 2020-12-17 10:10
    */
    <T> List<T> getBeansByType(Class<?> type);
    /**
     * @Description: 根据bean的类型，获取它BeanName集合
     * @param type  bean的类型
     * @return: java.util.List<java.lang.String>
     * @author: Caofeng
     * @mail: 867403822@qq.com       
     * @date: 2020-12-17 10:06
    */
    List<String> getBeanNamesByType(Class<?> type);
}
