package com.martin.mybatis.executor;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 20:08
 */
public interface Executor {

    /**
     *
     * @param mappedStatement
     *            获取sql语句和入参出参等信息
     * @param configuration
     *            获取数据源对象
     * @param param
     *            入参对象
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);
}
