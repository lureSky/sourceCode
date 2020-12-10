package com.martin.mybatis.sqlSource;

import com.martin.mybatis.sqlNode.MixedSqlNode;
import com.martin.mybatis.sqlNode.SqlNode;

/**
 * 处理带传参的 #{} 的sql语句
 * @author caofeng
 * @date 2020/12/10 10:07
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(MixedSqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(null);
        rootSqlNode.apply(context);
        //先对sql结点进行解析

        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        sqlSource =sqlSourceParser.parse(context.getSql());
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // 从staticSqlSource中获取相应信息
        return sqlSource.getBoundSql(param);
    }
}
