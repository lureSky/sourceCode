package com.martin.mybatis.sqlSession;

/**
 * @author caofeng
 * @date 2020/12/10 19:22
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
