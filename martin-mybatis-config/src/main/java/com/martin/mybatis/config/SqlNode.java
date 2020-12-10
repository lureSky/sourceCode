package com.martin.mybatis.config;

/**
 * 提供对Sql脚本的解析
 * @author caofeng
 * @date 2020/12/10 9:41
 */
public interface SqlNode {

    //解析SqlNode，利用动态上下文
    boolean apply(DynamicContext context);
}
