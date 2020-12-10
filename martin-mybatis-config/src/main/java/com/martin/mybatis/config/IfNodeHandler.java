package com.martin.mybatis.config;

import org.dom4j.Element;

import java.util.List;

/**
 * @author caofeng
 * @date 2020/12/10 14:10
 */
public class IfNodeHandler implements NodeHandler{


    @Override
    public void handlerNode(Element nodeToHandle, List<SqlNode> targetContents) {
        //TODO 需要将刚方法进行公共集抽取
        //对if标签进行解析
        XMLScriptParser xmlScriptParser = new XMLScriptParser(null);
        MixedSqlNode rootSqlNode = xmlScriptParser.parseDynamicTags(nodeToHandle);

        //if标签一般跟着一个test判定条件
        String test = nodeToHandle.attributeValue("test");
        IfSqlNode ifSqlNode = new IfSqlNode(test,rootSqlNode);
        targetContents.add(ifSqlNode);
    }
}
