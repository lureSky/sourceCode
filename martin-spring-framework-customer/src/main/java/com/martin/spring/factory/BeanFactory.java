package com.martin.spring.factory;

/**
 * 是基础容器的顶级接口，也是spring容器的最重要的接口
 *
 * @author caofeng
 * @date 2020/12/17 10:03
 */
public interface BeanFactory {

    /**
     * @Description: 根据bean的内容获取指定的bean实例
     * @param name
     * @return: java.lang.Object
     * @author: Caofeng
     * @mail: 867403822@qq.com
     * @date: 2020-12-17 10:04
    */
    Object getBean(String name);


}
