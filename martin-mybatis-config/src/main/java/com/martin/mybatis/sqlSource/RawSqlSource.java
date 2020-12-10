package com.martin.mybatis.sqlSource;

import com.martin.mybatis.config.BoundSql;
import com.martin.mybatis.sqlNode.MixedSqlNode;
import com.martin.mybatis.sqlNode.SqlNode;

/**
 * 处理带传参的 #{} 的sql语句
 * @author caofeng
 * @date 2020/12/10 10:07
 */
public class RawSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public RawSqlSource(MixedSqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
        //先对sql结点进行解析
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
