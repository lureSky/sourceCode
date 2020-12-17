package com.martin.spring.registry;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例Bean缓存管理
 * @author caofeng
 * @date 2020/12/17 10:32
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    /**
     * 使用线程安全的map类
     */
    private final Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String name, Object bean) {
        this.singletonObjects.put(name,bean);
    }

    @Override
    public Object getSingleton(String name) {
        return this.singletonObjects.get(name);
    }

    @Override
    public boolean containsSingleton(String name) {
        return this.singletonObjects.containsKey(name);
    }

    @Override
    public String[] getSingletonNames() {
//        return this.singletonObjects.keySet();
        return null;
    }

    @Override
    public int getSingletonCount() {
        return this.singletonObjects.size();
    }

    @Override
    public Object getSingletonMutex() {
        return this.singletonObjects;
    }
}
