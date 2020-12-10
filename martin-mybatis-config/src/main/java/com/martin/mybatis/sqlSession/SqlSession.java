package com.martin.mybatis.sqlSession;

import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 19:27
 */
public interface SqlSession {

    <T> T selectOne(String statementId, Object param);

    <T> List<T> selectList(String statementId, Object param);
}
