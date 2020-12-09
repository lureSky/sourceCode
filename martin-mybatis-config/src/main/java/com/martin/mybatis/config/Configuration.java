package com.martin.mybatis.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private DataSource dataSource;
    /**
     * mybatis是使用了strictMap，继承了HashMap，多了个参数name
     * ID+对象，框架内id是包装到了MappedStatement中
    */
    private Map<String,MappedStatement> mappedStatements = new HashMap<>();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setMappedStatement(String statementId, MappedStatement mappedStatement) {
        mappedStatements.put(statementId,mappedStatement);
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public MappedStatement getMappedStatementById(String statementId) {
        return mappedStatements.get(statementId);
    }
}
