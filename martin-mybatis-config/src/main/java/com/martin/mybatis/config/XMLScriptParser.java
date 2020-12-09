package com.martin.mybatis.config;

import org.dom4j.Element;

/**
    该类主要是解决Sql片段/脚本的，尽量Sql片段用其解析
 */
public class XMLScriptParser {

    private Configuration configuration;
    public XMLScriptParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析对象到SqlSource内
    */
    public SqlSource parseScriptNode(Element selectElement) {
        return null;
    }
}
