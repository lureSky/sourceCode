package com.martin.mybatis.sqlSource;

import com.martin.mybatis.sqlNode.MixedSqlNode;
import com.martin.mybatis.sqlNode.SqlNode;

/**
 * 专门封装和处理 动态标签  ${}的sql语句
 * @author caofeng
 * @date 2020/12/10 10:07
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public DynamicSqlSource(MixedSqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    /**
     * @Description: 在方法SqlSession执行的之后才调用该方法
     * @param param
     * @return: com.martin.mybatis.config.BoundSql
     * @author: Caofeng
     * @mail: 867403822@qq.com
     * @date: 2020-12-10 10:09
    */
    @Override
    public BoundSql getBoundSql(Object param) {
        //首先调用SqlNode将标签和#{}处理下
        DynamicContext context = new DynamicContext(param);
        rootSqlNode.apply(context);

        //调用sqlSourceParser处理#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        SqlSource sqlSource = sqlSourceParser.parse(context.getSql());
        return sqlSource.getBoundSql(param);
    }
}
