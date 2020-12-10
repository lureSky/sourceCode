package com.martin.mybatis.sqlNode;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.sqlSource.DynamicContext;

/**
 * @author caofeng
 * @date 2020/12/10 15:11
 */
public class WhereSqlNode implements SqlNode{

    private Configuration configuration;
    private MixedSqlNode mixedSqlNode;

    public WhereSqlNode(Configuration configuration, MixedSqlNode rootSqlNode) {
        this.configuration = configuration;
        this.mixedSqlNode = rootSqlNode;
    }

    @Override
    public boolean apply(DynamicContext context) {
        return false;
    }
}
