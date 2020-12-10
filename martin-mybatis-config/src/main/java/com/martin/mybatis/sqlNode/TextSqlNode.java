package com.martin.mybatis.sqlNode;

import com.martin.mybatis.config.DynamicContext;

/**
 * @author caofeng
 * @date 2020/12/10 10:17
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(sqlText);
        return true;
    }

    //如果有${就是动态的
    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }else{
            return false;
        }
    }
}
