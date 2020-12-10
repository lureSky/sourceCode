package com.martin.mybatis.config;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类主要是解决Sql片段/脚本的，尽量Sql片段用其解析
 */
public class XMLScriptParser {

    private Configuration configuration;

    private Map<String,NodeHandler> nodeHandlerMap = new HashMap<>();

    //是否是属于动态标签，如果是则修改
    private boolean isDynamic = false;

    public XMLScriptParser(Configuration configuration) {
        this.configuration = configuration;
        initNodeHandlerMap();
    }

    private void initNodeHandlerMap() {
        nodeHandlerMap.put("if",new IfNodeHandler());
        nodeHandlerMap.put("where",new WhereNodeHandler());
        nodeHandlerMap.put("foreach",new ForeachNodeHandler());
    }

    /**
     * 解析对象到SqlSource内
     * 1.先将sql脚本按照不同的类型封装到不同的SqlNode中
     * 2.将SqlNode集合封装到SqlSource中
     * 由于SqlSource带有#{} ${}  动态标签  处理方式不同，所以需要封装到不同的SqlSource中（staticSqlSource DynamicSqlSource    rawSqlSource）
     * 存在几种情况，如果只存在#{}才单独处理
     * 如果存在#{} 和 ${}   则直接按照${}处理
     */
    public SqlSource parseScriptNode(Element selectElement) {
        MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
        SqlSource sqlSource;
        if (isDynamic) {
            //处理动态标签
            sqlSource = new DynamicSqlSource(rootSqlNode);
        } else {
            //处理非动态
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        return sqlSource;
    }


    /**
     * @param selectElement
     * @Description: 方法解析，解析动态标签，封装到不同的SqlNode中
     * @return: com.martin.mybatis.config.MixedSqlNode
     * @author: Caofeng
     * @mail: 867403822@qq.com
     * @date: 2020-12-10 10:04
     */
    public MixedSqlNode parseDynamicTags(Element selectElement) {
        //先得到SqlNode
        List<SqlNode> contents = new ArrayList<>();
        int nodeCount = selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            //如果是文本则封装到TestSqlNode或者staticSqlNode
            //如果存在子标签类型，则递归解析
            if (node instanceof Text) {
                String sqlText = node.getText().trim();
                //判断sqlText是否存在,如果不存在就遍历其他节点
                if (sqlText == null || "".equals(sqlText)) {
                    continue;
                }
                TextSqlNode sqlNode = new TextSqlNode(sqlText);
                //如果sqlnode是动态的和其余情况
                if (sqlNode.isDynamic()) {
                    //如果是动态的
                    contents.add(sqlNode);
                    isDynamic = true;
                } else {
                    //非动态的
                    contents.add(new StaticTextSqlNode(sqlText));
                }
            } else if (node instanceof Element) {
                //子标签的，需要遍历子标签解析   if where foreach等
                //根据标签名称，封装到不同的节点信息
                String nodeName = node.getName().toLowerCase();
                //根据nodeName获取不同的处理（有ifSqlNode WhereSqlNode）
                NodeHandler nodeHandler = nodeHandlerMap.get(nodeName);
                nodeHandler.handlerNode((Element) node,contents);
                isDynamic = true;
            }
        }
        return new MixedSqlNode(contents);
    }
}
