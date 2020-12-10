package com.martin.mybatis.config;

import com.martin.mybatis.utils.OgnlUtils;

/**
 * @author caofeng
 * @date 2020/12/10 14:20
 */
public class IfSqlNode implements SqlNode{

    private String test;
    private MixedSqlNode rootSqlNode;

    public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public boolean apply(DynamicContext context) {
        //test需要判断是true还是false，只有为true才执行
        boolean evaluateBoolean = OgnlUtils.evaluateBoolean(test,context.getBindings().get("_parameter"));
        if(evaluateBoolean){
            rootSqlNode.apply(context);
            return true;
        }else {
            return false;
        }
    }
}
