package com.martin.mybatis.config;

import org.dom4j.Element;

public class XMLStatementParser {

    private Configuration configuration;

    public XMLStatementParser(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element selectElement, String namespace) {
        // 一个MappedStatement对象，就对应一个select标签
        String id = selectElement.attributeValue("id");
        //获取类型以后可能存在别名，注意
        String parameterType = selectElement.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveClass(parameterType);

        String resultType = selectElement.attributeValue("resultType");
        Class<?> resultTypeClass = resolveClass(resultType);

        String statementType = selectElement.attributeValue("statementType");
        statementType = statementType == null || statementType.equals("") ? "prepared" : statementType;

        // SqlSource就是封装了SQL语句
        // 此时封装的SQL语句是没有进行处理的，什么时候处理呢？
        // 处理时机，就是在SqlSession执行的时候
        // SELECT * FROM user WHERE id = #{id}
        // String sqlText = selectElement.getTextTrim();
        // SqlSource sqlSource = new SqlSource(sqlText);
        //SqlSource中包含多个SqlNode，SqlNode就是对一条完整sql中不同部分的封装，比如if，where等标签就属于不同的SqlNode
        SqlSource sqlSource = createSqlSource(selectElement);

        String statementId = namespace + "." + id;

        // 使用构建者模式改造
        MappedStatement.Builder mappedStatementBuilder = new MappedStatement.Builder(statementId, parameterTypeClass, resultTypeClass, statementType, sqlSource);
        //在build的时候需要进行判断是否存在
        MappedStatement mappedStatement = mappedStatementBuilder.build();
        //因为Configuration只有一个，而Curd都对应一个MappedStatement，因此MappedStatement是Map
        configuration.setMappedStatement(statementId, mappedStatement);
    }

    /**
     * 创建SqlSource其实就是对select等CRUD标签中的sql脚本进行处理
     *
     * @param selectElement
     * @return
     */
    private SqlSource createSqlSource(Element selectElement) {
        //返回node的数量
        XMLScriptParser scriptParser = new XMLScriptParser(configuration);
        SqlSource sqlSource = scriptParser.parseScriptNode(selectElement);
        return sqlSource;
    }

    //反射实现解析类
    private Class<?> resolveClass(String parameterType) {
        try {
            return Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
