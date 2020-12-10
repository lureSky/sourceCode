package com.martin.mybatis.sqlNode;

import com.martin.mybatis.config.DynamicContext;

/**
 * @author caofeng
 * @date 2020/12/10 14:01
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public boolean apply(DynamicContext context) {
        return false;
    }
}
