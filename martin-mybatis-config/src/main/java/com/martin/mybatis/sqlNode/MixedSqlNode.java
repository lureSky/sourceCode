package com.martin.mybatis.sqlNode;

import com.martin.mybatis.sqlSource.DynamicContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 10:00
 */
public class MixedSqlNode implements SqlNode {

    //封装所有的sqlNode
    private List<SqlNode> sqlNodes = new ArrayList<>();

    public MixedSqlNode(List<SqlNode> sqlNodes){
        this.sqlNodes = sqlNodes;
    }

    @Override
    public boolean apply(DynamicContext context) {
        //集合的数据封装
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
        return true;
    }
}
