package com.martin.mybatis.executor;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 20:07
 */
public class CachingExecutor implements Executor{

    // 基本执行器
    private Executor delegate;

    //代理模式
    public CachingExecutor(Executor delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        // 从二级缓存中根据sql语句获取处理结果（二级缓存怎么存？？？？？）
        // 如果有，则直接返回，如果没有则继续委托给基本执行器去吃力
        //目前已经不使用二级缓存
        return delegate.query(mappedStatement, configuration, param);
    }
}
