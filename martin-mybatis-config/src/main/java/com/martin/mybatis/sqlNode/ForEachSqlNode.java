package com.martin.mybatis.sqlNode;

import com.martin.mybatis.config.Configuration;
import com.martin.mybatis.config.DynamicContext;

/**
 * @author caofeng
 * @date 2020/12/10 15:07
 */
public class ForEachSqlNode implements SqlNode{

    private Configuration configuration;
    private MixedSqlNode mixedSqlNode;
    private String collection;
    private String item;
    private String index;
    private String open;
    private String close;
    private String separator;

    public ForEachSqlNode(Configuration configuration, MixedSqlNode rootSqlNode, String collection, String item, String index, String open, String close, String separator) {
        this.configuration = configuration;
        this.mixedSqlNode = rootSqlNode;
        this.collection = collection;
        this.item = item;
        this.index = index;
        this.open = open;
        this.close  = close;
        this.separator = separator;
    }

    @Override
    public boolean apply(DynamicContext context) {
        //写出forEach流程
        return false;
    }
}
