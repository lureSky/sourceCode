package com.martin.mybatis.sqlSession;

import com.martin.mybatis.config.Configuration;

/**
 * @author caofeng
 * @date 2020/12/10 19:26
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
