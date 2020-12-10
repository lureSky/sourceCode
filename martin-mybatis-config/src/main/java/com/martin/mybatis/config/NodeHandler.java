package com.martin.mybatis.config;

import org.dom4j.Element;

import java.util.List;

/**
 * 针对不同子标签进行处理，处理结束后封装到对应的sqlNode中
 *
 * 比如if-》ifSqlNode->DynamicSqlSource
 * @author caofeng
 * @date 2020/12/10 14:05
 */
public interface NodeHandler {

    void handlerNode(Element nodeToHandle, List<SqlNode> targetContents);

}
