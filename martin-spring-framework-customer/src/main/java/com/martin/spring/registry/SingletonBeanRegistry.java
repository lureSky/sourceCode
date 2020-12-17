package com.martin.spring.registry;

/**
 * 操作单例Bean对象的相关操作
 * @author caofeng
 * @date 2020/12/17 10:27
 */
public interface SingletonBeanRegistry {

    /**
     * 1.注册单例Bean对象
     * @param name  名称
     * @param bean  对象
     */
    void registerSingleton(String name,Object bean);

    /**
     * 2.获取单例Bean对象
     * @param name  bean名称
     * @return
     */
    Object getSingleton(String name);


    /**
     * 判断是否存在该名称的单例对象
     * @param name
     * @return
     */
    boolean containsSingleton(String name);

    /**
     * 获取所有的单例对象name
     */
    String[] getSingletonNames();

    /**
     * 获取目前单例对象的数量
     * @return
     */
    int getSingletonCount();

    /**
     * 获取单例集合对象（锁形式）
     * @return
     */
    Object getSingletonMutex();
}
